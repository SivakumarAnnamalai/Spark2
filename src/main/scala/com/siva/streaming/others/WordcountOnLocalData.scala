package com.siva.streaming.others

import com.siva.common.UserConstants

/**
  * Created by Sivakumar on 12/8/2017.
  */
object WordcountOnLocalData extends App with UserConstants{
  //https://github.com/apache/spark/blob/master/examples/src/main/scala/org/apache/spark/examples/sql/streaming/StructuredKafkaWordCount.scala
  val ssc = getStreamingContext("Wordcount on Local Data")
  val localDir = "/spark/streaming/input"

  val spark = getSparkSession("Wordcount on Local Data");
  spark.readStream.format("text")

  val lines = ssc.textFileStream(localDir)
  computeWordcountOnDStreams(lines).print()

  ssc.start()
  ssc.awaitTermination()
}
