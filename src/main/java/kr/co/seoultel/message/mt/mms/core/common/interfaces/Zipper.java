package kr.co.seoultel.message.mt.mms.core.common.interfaces;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public interface Zipper {
    static byte[] toZip(String str) {
        return toZip(str.getBytes(StandardCharsets.UTF_8));
    }

    static byte[] toZip(byte[] bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        try (DeflaterOutputStream out = new DeflaterOutputStream(baos, deflater)) {
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            deflater.end();
        }
        return baos.toByteArray();
    }

    static String fromZip(byte[] bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Inflater inflater = new Inflater();

        try (InflaterInputStream in = new InflaterInputStream(new ByteArrayInputStream(bytes), inflater)) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            return new String(baos.toByteArray(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError(e);
        } finally {
            inflater.end();
        }
    }
}
