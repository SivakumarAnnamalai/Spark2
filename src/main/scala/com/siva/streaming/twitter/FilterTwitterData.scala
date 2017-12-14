package com.siva.streaming.twitter

import com.siva.common.UserConstants
import org.apache.spark.streaming.twitter.TwitterUtils

/**
  * Created by Sivakumar on 12/8/2017.
  */
object FilterTwitterData extends UserConstants{

  def main(args:Array[String]){

    val ssc = getStreamingContext("Filter Twitter Data")
    setTwitterProperties()

    val filters = Array("spark","hadoop","hive","bigdata")
    val stream = TwitterUtils.createStream(ssc, None,filters)
    stream.print(5)

    ssc.start()
    ssc.awaitTermination()
  }
}
