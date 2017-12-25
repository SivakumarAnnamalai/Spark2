package com.siva.kafka.partition;

/**
 * Created by sivakumaran on 12/13/2017.
 */
public class HashCodeTest {
    public static void main(String args[]){
        String keys[] = "key1,key2,key3".split(",");
        int partitions = 3;
        for(String key:keys){
            System.out.println(key+": Expected Partition-"+ (key.hashCode()%partitions));
        }
    }
}
