package com.siva.spark.streaming.twitter

import com.siva.spark.common.UserConstants
import org.apache.spark.streaming.twitter.TwitterUtils

/**
  * Created by Sivakumar on 12/8/2017.
  */
object SortOnStreamsData extends UserConstants{

  def main(args:Array[String]){

    val ssc = getStreamingContext("Sort Country by count")
    setTwitterProperties()

    val filters = Array("spark","hadoop","hive","bigdata")
    val stream = TwitterUtils.createStream(ssc, None,filters)
    val countryDetails = stream.map(x=>(x.getLang,1)).reduceByKey(_+_).map(x=>(x._2,x._1)).transform(x=>x.sortByKey(false))
    countryDetails.print(5)

    ssc.start()
    ssc.awaitTermination()
  }
}
