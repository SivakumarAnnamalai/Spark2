package com.siva.core.basic

import com.siva.common.UserConstants
import com.siva.core.basic.CreateRDD.{LOCAL, getSparkContext}

/**
  * Created by Sivakumar on 12/8/2017.
  */
object CommonTransformations extends App with UserConstants{

  val sc = getSparkContext("Common Transformations Example")

  // Converting the String Collection to RDD
  var l1 = Array("abc xyz abc","xyz mnp abc")
  val rdd1 = sc.makeRDD(l1)

  // map is one of the transformation.
  // This has 1 to 1 relationship
  // For every input it produces one output
  val mapRDD = rdd1.map(x=>x.toUpperCase)

  // flatMap is one of the transformation.
  // This has 1 to n relationship. n>=1
  // For every input it produces one or more outputs
  val flatMapRDD = rdd1.flatMap(x=>x.split(" "))

  // filter is one of the transformation.
  // This has 1 to 0/1 relationship. n=0 or n=1
  // For every input it produces one output or no output
  val filterRDD = rdd1.filter(x=>x.contains("mnp"))

}
