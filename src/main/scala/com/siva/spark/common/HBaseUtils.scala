package com.siva.spark.common

import kafka.utils.ZkUtils
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{ConnectionFactory, Put, Scan}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.kafka.common.TopicPartition
import org.apache.spark.streaming.kafka.OffsetRange

/**
  * Created by Sivakumar on 12/25/2017.
  */
class HBaseUtils {
  def saveOffsets(TOPIC_NAME:String,GROUP_ID:String,offsetRanges:Array[OffsetRange],
                  hbaseTableName:String,batchTime: org.apache.spark.streaming.Time) ={
    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.addResource("src/main/resources/hbase-site.xml")
    val conn = ConnectionFactory.createConnection(hbaseConf)
    val table = conn.getTable(TableName.valueOf(hbaseTableName))
    val rowKey = TOPIC_NAME + ":" + GROUP_ID + ":" +String.valueOf(batchTime.milliseconds)
    val put = new Put(rowKey.getBytes)
    for(offset <- offsetRanges){
      put.addColumn(Bytes.toBytes("offsets"),Bytes.toBytes(offset.partition.toString),
        Bytes.toBytes(offset.untilOffset.toString))
    }
    table.put(put)
    conn.close()
  }

  /* Returns last committed offsets for all the partitions of a given topic from HBase in
following  cases.
*/

  def getLastCommittedOffsets(TOPIC_NAME:String,GROUP_ID:String,hbaseTableName:String,
                              zkQuorum:String,zkRootDir:String,sessionTimeout:Int,connectionTimeOut:Int):Map[TopicPartition,Long] ={

    val hbaseConf = HBaseConfiguration.create()
    val zkUrl = zkQuorum+"/"+zkRootDir
    val zkClientAndConnection = ZkUtils.createZkClientAndConnection(zkUrl,
      sessionTimeout,connectionTimeOut)
    val zkUtils = new ZkUtils(zkClientAndConnection._1, zkClientAndConnection._2,false)
    val zKNumberOfPartitionsForTopic = zkUtils.getPartitionsForTopics(Seq(TOPIC_NAME
    )).get(TOPIC_NAME).toList.head.size
    zkClientAndConnection._1.close()
    zkClientAndConnection._2.close()

    //Connect to HBase to retrieve last committed offsets
    val conn = ConnectionFactory.createConnection(hbaseConf)
    val table = conn.getTable(TableName.valueOf(hbaseTableName))
    val startRow = TOPIC_NAME + ":" + GROUP_ID + ":" +
      String.valueOf(System.currentTimeMillis())
    val stopRow = TOPIC_NAME + ":" + GROUP_ID + ":" + 0
    val scan = new Scan()
    val scanner = table.getScanner(scan.setStartRow(startRow.getBytes).setStopRow(
      stopRow.getBytes).setReversed(true))
    val result = scanner.next()
    var hbaseNumberOfPartitionsForTopic = 0 //Set the number of partitions discovered for a topic in HBase to 0
    if (result != null){
      //If the result from hbase scanner is not null, set number of partitions from hbase to the  number of cells
      hbaseNumberOfPartitionsForTopic = result.listCells().size()
    }

    val fromOffsets = collection.mutable.Map[TopicPartition,Long]()

    if(hbaseNumberOfPartitionsForTopic == 0){
      // initialize fromOffsets to beginning
      for (partition <- 0 to zKNumberOfPartitionsForTopic-1){
        fromOffsets += (new TopicPartition(TOPIC_NAME,partition) -> 0)
      }
    } else if(zKNumberOfPartitionsForTopic > hbaseNumberOfPartitionsForTopic){
      // handle scenario where new partitions have been added to existing kafka topic
      for (partition <- 0 to hbaseNumberOfPartitionsForTopic-1){
        val fromOffset = Bytes.toString(result.getValue(Bytes.toBytes("offsets"),
          Bytes.toBytes(partition.toString)))
        fromOffsets += (new TopicPartition(TOPIC_NAME,partition) -> fromOffset.toLong)
      }
      for (partition <- hbaseNumberOfPartitionsForTopic to zKNumberOfPartitionsForTopic-1){
        fromOffsets += (new TopicPartition(TOPIC_NAME,partition) -> 0)
      }
    } else {
      //initialize fromOffsets from last run
      for (partition <- 0 to hbaseNumberOfPartitionsForTopic-1 ){
        val fromOffset = Bytes.toString(result.getValue(Bytes.toBytes("offsets"),
          Bytes.toBytes(partition.toString)))
        fromOffsets += (new TopicPartition(TOPIC_NAME,partition) -> fromOffset.toLong)
      }
    }
    scanner.close()
    conn.close()
    fromOffsets.toMap
  }
}
