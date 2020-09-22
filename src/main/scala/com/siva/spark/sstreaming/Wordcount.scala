package com.siva.spark.sstreaming

import com.siva.spark.common.UserConstants
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession

/**
  * Created by Sivakumar on 12/25/2017.
  */
object Wordcount extends App with UserConstants{

  val spark = SparkSession
    .builder
    .appName("StructuredNetworkWordCount")
    .getOrCreate()

  import spark.implicits._
    
}
