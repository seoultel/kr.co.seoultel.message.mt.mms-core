package kr.co.seoultel.message.mt.mms.core.entity.distribute;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
abstract class Distributor<T> {

    @Getter
    protected int index = 0;

    @Getter
    protected final int size;
    protected final List<T> list;

    public Distributor(List<T> list) {
        this.list = list;
        this.size = list.size();

        log.info("[Distributor] Successfully initiated distributor by data[{}]", list);
    }

    protected abstract T distribute();

    protected int nextIndex() {
        if (index + 1 >= list.size()) return 0;
        else return index + 1;
    }

    protected int next() {
        if (index + 1 >= list.size()) reset();
        return index++;
    }

    
    protected void reset() {
        this.index = 0;
    }
}
