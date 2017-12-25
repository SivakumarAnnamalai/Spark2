package com.siva.spark.core.basic

import com.siva.spark.common.UserConstants

/**
  * Created by Sivakumar on 12/8/2017.
  */
object CommonActions extends App with UserConstants{
  val sc = getSparkContext("Common Actions Example")

  // Converting the String Collection to RDD
  var l1 = Array("mnp abc","abc xyz abc","aa pqr","xyz mnp abc")
  val rdd1 = sc.makeRDD(l1)

  var l2 = List(1,2,3,4,5)
  val rdd2 = sc.makeRDD(l2)

  // count is one of the Action.
  // It will provide the number of elements on the RDD
  val countOutput = rdd1.count()
  println("Count Output: "+countOutput)

  // count is one of the Action.
  // It will provide the sum of all the elements in the RDD
  val reduceOutput = rdd2.reduce((x,y)=>x+y)
  println("Reduce Output: "+reduceOutput)

  // first is one of the Action.
  // It will provide the first element of the RDD
  val firstOutput = rdd1.first()
  println("First Output: "+firstOutput)

  // foreach is one of the action
  // this iterates the RDD and print the elements
  println("foreach Output: ")
  rdd1.foreach(println)

  // collect is one of the Action.
  // This will provide all the elements of the RDD as an Array
  val collectOutput = rdd1.collect()
  println("Collect Output: ")
  collectOutput.foreach(println)

  // take is one of the Action.
  // This will provide n number of elements of the RDD as an Array
  val takeOutput = rdd1.take(2)
  println("Take Output: ")
  takeOutput.foreach(println)

  // takeOrdered is one of the Action.
  // This will provide n number of elements of the RDD as an Array in ascending order
  val takeOrderedOutput = rdd1.takeOrdered(2)
  println("TakeOrdered Output: ")
  takeOrderedOutput.foreach(println)

  // SaveAsTextFile is one of the action
  // this stores the RDD output into a directory in text(human understandable) format
  rdd1.saveAsTextFile(OUTPUT_PATH+"out1")
  rdd2.saveAsTextFile(OUTPUT_PATH+"out2")

}
