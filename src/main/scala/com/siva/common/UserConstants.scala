package com.siva.common

import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.DStream
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

  // Kafka details
  val LOCALHOST = "localhost"
  val KAFKA_BROKERS = LOCALHOST+":9092"
  val METADATA_BROKER_LIST = "metadata.broker.list"

  def getSparkContext(appName:String,mode:String=LOCAL):SparkContext={
    val conf = new SparkConf()
    if(mode.equals(LOCAL))
      conf.setMaster("local[*]")
    else
      conf.setMaster(mode)
    conf.setAppName(appName)
    return new SparkContext(conf)
  }

  def getSparkSession(appName:String,mode:String=LOCAL): SparkSession ={
    return SparkSession
      .builder
      .appName(appName)
      .master(mode)
      .getOrCreate()
  }



  def getStreamingContext(appName:String,seconds:Int=5,mode:String=LOCAL):StreamingContext={
    val sc = getSparkContext(appName,mode);
    return new StreamingContext(sc, Seconds(seconds))
  }

  // Twitter details
  def setTwitterProperties(): Unit ={
    System.setProperty("twitter4j.oauth.consumerKey", "FCAmTEJEhwG8QSdf9YXxKdgS9")
    System.setProperty("twitter4j.oauth.consumerSecret", "7RELDjhS7z5fS5Rf4w3m7aHXJj633zulLgyInaiOrOlWgRjt5p")
    System.setProperty("twitter4j.oauth.accessToken", "272793385-u2WRrl1L8aeVgWz4HXKSERnevPWITSZl1yxMsJHu")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "goZkHbgkTAWYnb9vHihAEkg4lcz9jSe9ZQtDNHYTCPxs2")
  }

  def computeWordcountOnDStreams(lines:DStream[String]): DStream[(String,Int)] ={
    val words = lines.flatMap(_.split(" "))
    val wordsPair = words.map(x => (x, 1))
    return wordsPair.reduceByKey(_ + _)
  }


  def stateUpdateFunc = (values: Seq[Int], state: Option[Int]) => {
    val currentCount = values.foldLeft(0)(_ + _);
    val previousCount = state.getOrElse(0)
    Some(currentCount + previousCount)
  }




}
