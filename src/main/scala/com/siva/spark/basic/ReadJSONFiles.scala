package com.siva.spark.basic

import com.siva.spark.common.UserConstants

object ReadJSONFiles extends App with UserConstants{

  val spark = getSparkSession("Read JSON Files")
  val df1 = spark.read.
    format("json").
    load(DATASET_PATH+"customer_data.json")

  df1.printSchema()

  df1.show(5)
  }
