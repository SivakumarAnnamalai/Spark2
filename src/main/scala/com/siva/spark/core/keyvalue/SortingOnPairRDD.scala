package com.siva.spark.core.keyvalue

import com.siva.spark.common.UserConstants
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Sivakumar on 12/8/2017.
  */
object SortingOnPairRDD extends App with UserConstants{
  val sc = getSparkContext("Sorting Example")
  val r1 = sc.textFile(DATASET_PATH+"flight_delays.csv",2)

  // Actions examples
  println("No of Records:"+r1.count())
  println("No of Partitions:"+r1.getNumPartitions)
  val r2 = r1.map(x=>x.split(",")(10))
  r2.take(5).foreach(println)
  println("Descending Order")
  r2.distinct.takeOrdered(5)(Ordering[String].reverse).foreach(println)
  println("Ascending Order")
  r2.distinct.takeOrdered(5).foreach(println)
  val r3 = r1.map(x=>{val y = x.split(","); (y(1),y(10),y(12))})
  println("Ascending Order with three elements")
  r3.distinct.filter(x=>x._2.length>1).takeOrdered(5).foreach(println)
  println("Descending order with three elements")
  r3.distinct.takeOrdered(5)(Ordering.by[(String,String,String),String](_._2).reverse).foreach(println)
  r3.distinct().takeOrdered(5)(Ordering[(String,String,String)].on(x=>(x._2,x._3,x._1))).foreach(println)

}
