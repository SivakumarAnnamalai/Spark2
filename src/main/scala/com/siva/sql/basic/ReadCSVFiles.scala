package com.siva.sql.basic

import com.siva.common.UserConstants

object ReadCSVFiles extends App with UserConstants{

  val spark = getSparkSession("Read CSV Files")
  val df1 = spark.read.
    format("csv").
    load(DATASET_PATH+"flight_delays.csv")

  df1.printSchema()
  df1.show(2)

  // Option to specify the header
  val df2 = spark.read.
    format("csv").
    option("header","true").
    load(DATASET_PATH+"flight_delays.csv")

  df2.printSchema()
  df2.show(2)

  // Mention the delimiter value. This can be used to read tab, pipe, semicolon separated files
  val df3 = spark.read.
    format("csv").
    option("header","true").
    option("delimiter",",").
    load(DATASET_PATH+"flight_delays.csv")

  df3.printSchema()
  df3.show(2)

  // Infer Schema from files either int/float/boolean
  val df4 = spark.read.
    format("csv").
    option("header","true").
    option("delimiter",",").
    option("inferSchema","true").
    load(DATASET_PATH+"flight_delays.csv")

  df4.printSchema()
  df4.show(2)

}
