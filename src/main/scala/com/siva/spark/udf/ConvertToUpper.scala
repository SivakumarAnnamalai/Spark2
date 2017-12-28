package com.siva.spark.udf

import com.siva.spark.basic.ReadJSONFiles.{DATASET_PATH, spark}
import com.siva.spark.common.UserConstants

/**
  * Created by Sivakumar on 12/27/2017.
  */
object ConvertToUpper extends App with UserConstants{

  val toUpper = (s:String) => s.toUpperCase

  val spark = getSparkSession("Spark UDF example")
  spark.udf.register("toUpperUDF",toUpper)
  val df1 = spark.read.
    format("json").
    load(DATASET_PATH+"customer_data.json")
  df1.write.saveAsTable("Customer")
  spark.sql("select first_name,toUpperUDF(first_name) as FirstName from Customer limit 5").show
}
