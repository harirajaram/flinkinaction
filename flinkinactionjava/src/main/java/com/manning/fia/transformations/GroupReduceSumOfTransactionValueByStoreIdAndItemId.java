package com.manning.fia.transformations;

import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.util.Collector;

import com.manning.fia.model.petstore.TransactionItem;
public final class GroupReduceSumOfTransactionValueByStoreIdAndItemId
        implements
        GroupReduceFunction<TransactionItem,Tuple3<Integer,Integer, Double>> {
    

    @Override
    public void reduce(Iterable<TransactionItem> values,
            Collector<Tuple3<Integer, Integer, Double>> out) throws Exception {
        int storeId=-1;
        int itemId=-1;
        double total = 0d;
        for(TransactionItem value:values){
            storeId = value.storeId;
            itemId = value.itemId;
            total = total + value.transactionItemValue;
        }
        out.collect(new Tuple3<Integer,Integer,Double>(storeId,itemId,total));
    }
}