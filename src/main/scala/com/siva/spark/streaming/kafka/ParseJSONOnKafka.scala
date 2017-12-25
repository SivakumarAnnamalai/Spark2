package com.siva.spark.streaming.kafka

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.siva.spark.common.UserConstants
import com.siva.spark.core.basic.ParseJSON.Person
import kafka.serializer.StringDecoder
import org.apache.spark.streaming.kafka.KafkaUtils

/**
  * Created by Sivakumar on 12/8/2017.
  */
object ParseJSONOnKafka extends App with UserConstants{
  val ssc = getStreamingContext("DirectKafkaWordCount")
  val topics = "test-topic1"
  val topicsSet = topics.split(",").toSet
  val kafkaParams = Map[String, String](METADATA_BROKER_LIST -> KAFKA_BROKERS)

  val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
    ssc, kafkaParams, topicsSet)
  val lines = messages.map(_._2)
  val requiredData = lines.flatMap(record=>{
    val mapper = new ObjectMapper() with ScalaObjectMapper;
    mapper.registerModule(DefaultScalaModule)
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    Some(mapper.readValue(record, classOf[Person]))
  })
  requiredData.print()

  ssc.start()
  ssc.awaitTermination()
}
