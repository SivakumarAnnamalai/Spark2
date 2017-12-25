package com.siva.spark.streaming.socket

import com.siva.spark.common.UserConstants

/**
  * Created by Sivakumar on 12/8/2017.
  */
object StatefulWordcountOnSocket extends App with UserConstants{
  val ssc = getStreamingContext("Spark Streaming Wordcount")
  ssc.checkpoint("/tmp/checkpoint1")

  val lines = ssc.socketTextStream(LOCALHOST,9999)
  val currentBatchWordcount = computeWordcountOnDStreams(lines)
  println("Current Batch Details")
  currentBatchWordcount.print()

  println("Cumulative Details")
  val cumulativeWordcount = currentBatchWordcount.updateStateByKey(stateUpdateFunc)
  cumulativeWordcount.print()

  ssc.start()
  ssc.awaitTermination()
}
