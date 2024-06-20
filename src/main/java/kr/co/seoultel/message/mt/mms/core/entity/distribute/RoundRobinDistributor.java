package kr.co.seoultel.message.mt.mms.core.entity.distribute;

import java.util.List;

public class RoundRobinDistributor<T> extends Distributor<T> {

    public RoundRobinDistributor(List<T> list) {
        super(list);
    }

    @Override
    public T distribute() {
        return list.get(next());
    }
}
