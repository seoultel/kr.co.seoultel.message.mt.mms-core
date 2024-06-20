package kr.co.seoultel.message.mt.mms.core.entity.distribute;

import lombok.Getter;

import java.util.List;

abstract class Distributor<T> {

    @Getter
    protected int index = 0;

    @Getter
    protected final int size;
    protected final List<T> list;

    public Distributor(List<T> list) {
        this.list = list;
        this.size = list.size();
    }

    protected abstract T distribute();

    protected int next() {
        if (index + 1 >= list.size()) reset();
        return index++;
    }

    
    protected void reset() {
        this.index = 0;
    }
}
