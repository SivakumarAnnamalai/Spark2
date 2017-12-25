package com.siva.spark.basic

import com.siva.spark.common.UserConstants

object RDDToDF extends App with UserConstants{

  val spark = getSparkSession("RDD To DFF")
  val rdd1 = spark.sparkContext.makeRDD(List("e1 Ajith","e2 Vijay"))

  import spark.implicits._
  val df1 = rdd1.toDF()

  df1.show

  // Converting to tuple
  val rdd2 = rdd1.map(x=>{
    val y = x.split(" ");
    (y(0),y(1))
  }
  )
  val columns = Array("Eid","EName")

  // Creating the dataframe with column names
  val df2 = rdd2.toDF(columns:_*)
  df2.show()

  // converting dataframe to RDD
  val rdd3 = df2.rdd
  rdd3.foreach(println)

}
