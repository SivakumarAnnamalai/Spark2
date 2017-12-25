package com.siva.spark.streaming.twitter

import com.siva.spark.common.UserConstants
import org.apache.spark.streaming.twitter.TwitterUtils

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
