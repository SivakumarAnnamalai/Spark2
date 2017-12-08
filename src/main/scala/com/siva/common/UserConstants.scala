package com.siva.common

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Sivakumar on 12/8/2017.
  */
trait UserConstants {
  val BASE_PATH = "C:\\Users\\sivakumaran\\Downloads\\Spark2\\"
  val RESOURCES_PATH = BASE_PATH + "src\\main\\resources\\"
  val DATASET_PATH = RESOURCES_PATH + "datasets\\"
  val LOCAL = "local"

  def getSparkContext(appName:String,mode:String=LOCAL):SparkContext={
    val conf = new SparkConf()
    if(mode.equals(LOCAL))
      conf.setMaster("local[*]")
    else
      conf.setMaster(mode)
    conf.setAppName(appName)
    return new SparkContext(conf)
  }

  def getStreamingContext(appName:String,seconds:Int,mode:String=LOCAL):StreamingContext={
    val sc = getSparkContext(appName,mode);
    return new StreamingContext(sc, Seconds(seconds))
  }

  def setTwitterProperties(): Unit ={
    System.setProperty("twitter4j.oauth.consumerKey", "FCAmTEJEhwG8QSdf9YXxKdgS9")
    System.setProperty("twitter4j.oauth.consumerSecret", "7RELDjhS7z5fS5Rf4w3m7aHXJj633zulLgyInaiOrOlWgRjt5p")
    System.setProperty("twitter4j.oauth.accessToken", "272793385-u2WRrl1L8aeVgWz4HXKSERnevPWITSZl1yxMsJHu")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "goZkHbgkTAWYnb9vHihAEkg4lcz9jSe9ZQtDNHYTCPxs2")
  }



}
