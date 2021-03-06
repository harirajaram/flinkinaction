package com.manning.fia.transformations;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple5;

import com.manning.fia.model.petstore.TransactionItem;

@SuppressWarnings("serial")
public class StoreIdItemIdKeySelector3 implements KeySelector<Tuple3<Integer,  Integer, Double>, Tuple2<Integer,Integer>>{
    @Override
    public Tuple2<Integer,Integer> getKey(Tuple3<Integer,  Integer, Double> value) throws Exception {        
        return new Tuple2<Integer,Integer>(value.f0,value.f1);
    }

    
}
