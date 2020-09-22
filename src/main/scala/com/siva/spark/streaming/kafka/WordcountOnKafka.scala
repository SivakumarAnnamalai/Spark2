package com.siva.spark.streaming.kafka

import com.siva.spark.common.UserConstants
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by Sivakumar on 12/8/2017.
  */
object WordcountOnKafka extends App with UserConstants{
  val ssc = getStreamingContext("DirectKafkaWordCount")
  val topics = "test-topic1"
  val topicsSet = topics.split(",").toSet
  val kafkaParams = Map[String, String](METADATA_BROKER_LIST -> KAFKA_BROKERS)

  val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
    ssc, kafkaParams, topicsSet)
  val lines = messages.map(_._2)
  computeWordcountOnDStreams(lines).print()

  ssc.start()
  ssc.awaitTermination()
}
