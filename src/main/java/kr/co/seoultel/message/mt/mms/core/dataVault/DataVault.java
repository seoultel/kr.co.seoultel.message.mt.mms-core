package kr.co.seoultel.message.mt.mms.core.dataVault;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import kr.co.seoultel.message.mt.mms.core.common.serializer.LocalDateTimeSerializer;
import kr.co.seoultel.message.mt.mms.core.util.CommonUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


/**
 * @author 092600
 *
 * @summary
 * ================================================================================================================================
 * **** DataVault 는 메세지의 영속성을 보장하는 Redis 에 장애가 발생했을 때 메세지에 영속성이 깨지는 것을 방지하기 위한 목적으로 설계된 객체이다.          ****
 * **** 영속성을 보장하기 위한 ConcurrentHashMap 또는 ConcurrentLinkedQueue 를 가지고 있는 객체는 DataVault 객체 또한 가지도록 설계되어야 한다.  ****
 * ================================================================================================================================
 * ** 1. 어플리케이션이 종료될 떄(ContextClosedEvent Event 발생 시) **
 *       write() 또는 writeAll() 메서드를 호출하여 ConcurrentHashMap 또는 ConcurrentLinkedQueue 안의 데이터를 파일(path) 에 적도록 하여야 한다.
 * ** 2. 어플리케이션이 실행될 때 **
 *       애플리케이션이 실행될 때는 read() 또는 readAll() 메서드를 호출하여 ConcurrentHashMap 또는 ConcurrentLinkedQueue 안에
 *       파일(path) 에 작성한 데이터를 넣어 런타임에 메세지의 영속성을 보장해줄 수 있도록 설계되어야 한다.
 * ================================================================================================================================
 *
 * @param <T> the type parameter
 */
@Slf4j
public class DataVault<T> {

    private transient final Gson gson  = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
                                                            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                                                            .setPrettyPrinting().create();

    @Getter
    private final String name;
    private final String path;  // path to write


    /**
     * @param path 파일을 작성할 위치(path)
     */
    public DataVault(@NonNull String name, @NonNull String path) {
        this.name = name.toUpperCase();
        this.path = path;
    }



    public void init() {
        while (!createFile()) {
            CommonUtil.doThreadSleep(500L);
        }

        log.info("[{}] Successfully created DataVault[{}], read latest backup datas", name.toUpperCase(), path);
    }

    private boolean createFile() {
        File file = new File(path);
        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                log.error("Failed create backup file to {}", path);
            }

            return false;
        }

        return true;
    }


    /**
     * Write T data to file in path;
     *
     * @author 092600
     * @param data the data
     * @throws IOException the io exception
     *
     **/
    public void writeOne(T data) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
        bw.write(gson.toJson(data));

        bw.close();
    }

    /**
     * Write Collection<T> datas to file in path;
     *
     * @author 092600
     * @param data Collection<T>
     * @throws IOException the io exception
     *
     **/
    public void writeAll(Collection<T> data) throws IOException {
        try {
            String toJson = gson.toJson(data);
            FileWriter fw = new FileWriter(path, false);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(toJson);
                bw.flush();

                log.info("{} IS WRIET TO {} MESSAGES[SIZE : {}]", name, path, data.size());
            } catch (Exception e) {
                log.warn("[{}] An exception occurred", name, e);
            }
        } catch (Exception e) {
            log.error("[{}] An exception occurred while writing data to the file[{}].", name, path);
            log.error("[{}] data that caused the exception[{}]", name, data);
        }
    }

    public Optional<List<T>> readAll(Class<T> cls) {
        if (Files.exists(Path.of(this.path))) {
            try {
                JsonReader reader = new JsonReader(new FileReader(this.path));

                Type dataType = TypeToken.getParameterized(List.class, cls).getType();
                List<T> data = gson.fromJson(reader, dataType);
                return (data == null || data.isEmpty()) ? Optional.empty() : Optional.of(data);
            } catch (FileNotFoundException e) {
                log.error("[{}] is not exists path : {}", name, this.path);
                System.exit(0);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        return Optional.empty();
    }


    public void destroy(Collection<T> data) {
        boolean isDone = false;
        do {
            try {
                writeAll(data);
                isDone = true;
            } catch (IOException e) {
                log.error("Data backup is failed, retry after 500ms");
                CommonUtil.doThreadSleep(500L);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } while (!isDone);
    }

    public void clearFile() {
        try {
            File file = new File(path);
            try (FileWriter fw = new FileWriter(file)) {
                fw.write("[]");
            }
        } catch (IOException e) {
            log.error("[{}] FAILED TO CLEAR FILE[{}]", name, path);
        }
    }

}
