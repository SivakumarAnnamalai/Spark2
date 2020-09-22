package com.siva.spark.core.basic;

import com.siva.spark.common.UserConstantsJava;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * Created by sivakumaran on 12/30/2017.
 */
public class WordcountJava implements UserConstantsJava{
    public static void main(String args[]){
        SparkConf conf = new SparkConf();
        conf.setMaster(LOCAL);
        conf.setAppName("Wordcount on Java");

        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> r1 = sc.textFile(DATASET_PATH+"test.txt");
        JavaRDD<String> r2 = r1.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
        JavaPairRDD<String,Integer> r3 = r2.mapToPair(x -> new Tuple2<String,Integer>(x,1));
        JavaPairRDD<String,Integer> r4 = r3.reduceByKey((x,y) -> x+y );
        r4.foreach(x -> System.out.println(x));


    }
}
