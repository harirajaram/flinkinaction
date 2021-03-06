package com.manning.fia.c03;

import java.util.Arrays;
import java.util.List;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.api.java.tuple.Tuple7;

import com.manning.fia.transformations.ComputeTransactionValue;
import com.manning.fia.transformations.TransactionItemParser;

public class SimpleMapOperatorLocalClient {

    public static void main(String[] args) throws Exception {
        String[] transactionItemLines = {                                          
                "1000,1,1,1_item,5,1.0," + "20151231130000",
                "1000,1,2,2_item,10,100.0," + "20151231130000",
                "1000,1,3,3_item,3,200.0," + "20151231130000",
                "1001,2,1,1_item,4,1.0," + "20151231130000",
                "1001,2,2,2_item,11,100.0," + "20151231130000",
                "1001,2,3,3_item,7,200.0," + "20151231130000",
                "1002,3,1,1_item,4,1.0," + "20151231130000",
                "1002,3,2,2_item,11,100.0," + "20151231130000",
                "1002,3,3,3_item,7,200.0," + "20151231130000",
                "1003,4,1,1_item,4,1.0," + "20151231150000",
                "1003,4,2,2_item,11,100.0," + "20151231150000",
                "1003,4,3,3_item,7,200.0," + "20151231150000",
                "1004,5,1,1_item,4,1.0," + "20151231150000",
                "1004,5,2,2_item,11,100.0," + "20151231150000",
                "1004,5,3,3_item,7,200.0," + "20151231150000",
                "1005,6,1,1_item,4,1.0," + "20151231150000",
                "1005,6,2,2_item,11,100.0," + "20151231150000",
                "1005,6,3,3_item,7,200.0," + "20151231150000",
                "1006,7,1,1_item,4,1.0," + "20151231150000",
                "1006,7,2,2_item,11,100.0," + "20151231150000",
                "1006,7,3,3_item,7,200.0," + "20151231150000"
        };
        ExecutionEnvironment execEnv = ExecutionEnvironment
                                           .createLocalEnvironment();
        DataSet<String> source = execEnv                              
                                      .fromCollection(Arrays.asList(transactionItemLines));
        DataSet<Tuple7<Integer, Long, Integer, String, Integer, Double, Long>> tuples = 
                 source.map(new TransactionItemParser());             
        DataSet<Tuple5<Integer, Long, Integer, String, Double>> transformedTuples = 
                 tuples.map(new ComputeTransactionValue());           
        List<Tuple5<Integer, Long, Integer, String, Double>> output = 
                 transformedTuples.collect();                             
        for (Tuple5<Integer, Long, Integer, String, Double> line : output) {         
            System.out.println(line);
        }

    }
}
