package com.siva.kafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * Created by Sivakumar on 12/13/2017.
 */
public class CustomPartitioner implements Partitioner {

    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return new String(keyBytes).hashCode() % cluster.partitionCountForTopic(topic);
    }

    public void close() {
    }

    public void configure(Map<String, ?> map) {
    }
}
