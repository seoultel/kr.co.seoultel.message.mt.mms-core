package kr.co.seoultel.message.mt.mms.core.entity.distribute;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RoundRobinDistributor<T> extends Distributor<T> {

    public RoundRobinDistributor(List<T> list) {
        super(list);
    }

    @Override
    public T distribute() {
        return list.get(next());
    }
}
