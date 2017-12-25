package com.siva.spark.core.basic

import com.siva.spark.common.UserConstants
import com.siva.spark.core.basic.CommonTransformations.getSparkContext

/**
  * Created by Sivakumar on 12/8/2017.
  */
object MathematicalTransformations extends App with UserConstants{

  val sc = getSparkContext("Mathematical Transformations Example")

  // Converting the String Collection to RDD
  var l1 = Array("Hadoop","Hive","Spark","Kafka","Hadoop")
  val rdd1 = sc.makeRDD(l1)

  // Converting the String Collection to RDD
  var l2 = Array("HBase","Hive","Pig")
  val rdd2 = sc.makeRDD(l2)

  // distinct operation is one of the transformation
  // this will provide the unique elements of the rdd
  val distinctRDD = rdd1.distinct()
  println("Distinct RDD output")
  distinctRDD.foreach(println)

  // union operation is one of the transformation
  // this will provide the combination of elements rdd1 and rdd2
  // this will not remove the duplicates
  val unionRDD = rdd1.union(rdd2)
  println("Union RDD output")
  unionRDD.foreach(println)

  // intersectioin operation is one of the transformation
  // this will provide common elements between rdd1 and rdd2
  val intersectionRDD = rdd1.intersection(rdd2)
  println("Intersection RDD output")
  intersectionRDD.foreach(println)

  // subtract operation is one of the transformation
  // this will provide elements which are present in rdd1 but not on rdd2
  val subtractRDD = rdd1.subtract(rdd2)
  println("Subtract RDD output")
  subtractRDD.foreach(println)

  // cartesian operation is one of the transformation
  // this will provide the result as tuple
  // this is combination of rdd1 and rdd2
  val cartesianRDD = rdd1.cartesian(rdd2)
  println("Cartesian RDD output")
  cartesianRDD.foreach(println)

}
