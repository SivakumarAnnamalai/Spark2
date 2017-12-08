package com.siva.streaming.twitter

import com.siva.common.UserConstants
import com.siva.core.basic.Caching.{LOCAL, getSparkContext}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Sivakumar on 12/8/2017.
  */
object TwitterExample1 extends UserConstants{

  def main(args:Array[String]){
    val ssc = getStreamingContext("TwitterExample1",5)
    setTwitterProperties()
    val stream = TwitterUtils.createStream(ssc, None)
    stream.print(5)
    ssc.start()
    ssc.awaitTermination()
  }


}
