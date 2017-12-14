package com.siva.streaming.twitter

import com.siva.common.UserConstants
import org.apache.spark.streaming.twitter.TwitterUtils

/**
  * Created by Sivakumar on 12/8/2017.
  */
object GetCountryDetails extends UserConstants{

  def main(args:Array[String]){

    val ssc = getStreamingContext("Get Country Details")
    setTwitterProperties()

    val filters = Array("spark","hadoop","hive","bigdata")
    val stream = TwitterUtils.createStream(ssc, None,filters)
    val countryDetails = stream.map(x=>(x.getLang,1)).reduceByKey(_+_)
    countryDetails.print(5)

    ssc.start()
    ssc.awaitTermination()
  }
}
