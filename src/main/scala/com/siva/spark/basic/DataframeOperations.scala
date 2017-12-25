package com.siva.spark.basic

import com.siva.spark.common.UserConstants

object DataframeOperations extends App with UserConstants{

  val spark = getSparkSession("Data frame operations")
  val df1 = spark.read.
    format("csv").
    option("header","true").
    option("inferSchema","true").
    load(DATASET_PATH+"flight_delays.csv")

  // Restricting/selecting the required columns
  val df2 = df1.select("Year","Month","DayOfMonth")
  df2.show(3)

  // Filtering the dataframe based on the condition
  val df3 = df1.filter(df1("DayOfMonth")>8)
  df3.show(5)

  df2.write.
    format("parquet").
    save(OUTPUT_PATH+"flights_parquet")

  val df4 = spark.read.load(OUTPUT_PATH+"flights_parquet")
  df4.show(2)


  }
