package com.siva.spark.streaming.socket

import com.siva.spark.common.UserConstants

/**
  * Created by Sivakumar on 12/8/2017.
  */
object WordcountOnSocket extends App with UserConstants{
  val ssc = getStreamingContext("Spark Streaming Wordcount")

  val lines = ssc.socketTextStream(LOCALHOST,9999)
  computeWordcountOnDStreams(lines).print()

  ssc.start()
  ssc.awaitTermination()
}
