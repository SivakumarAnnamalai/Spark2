package com.siva.spark.streaming.others

import com.siva.spark.common.UserConstants

/**
  * Created by Sivakumar on 12/8/2017.
  */
object WordcountOnHDFSData extends App with UserConstants{
  val ssc = getStreamingContext("Wordcount on HDFS Data")
  val hdfsDir = "/spark/streaming/input"
  val lines = ssc.textFileStream(hdfsDir)
  computeWordcountOnDStreams(lines).print()

  ssc.start()
  ssc.awaitTermination()
}
