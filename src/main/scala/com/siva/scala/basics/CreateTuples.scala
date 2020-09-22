package com.siva.scala.basics

/**
  * Created by Sivakumar on 12/11/2017.
  */
object CreateTuples extends App{

  // Tuple is nothing but key/value pair.
  var v1 = (10,20)
  println("Tuple v1 value:"+v1)
  println("First Element:" + v1._1)  // Tuple can be accessed by position. Position starts from 1 not 0
  println("Second Element:" + v1._2)

  // Be clear that, tuple is not an array.
  // Tuple Can have different datatypes
  var v2 = (15,"Spark")
  println("Tuple v2 value:"+v2)
  println("First Element:" + v2._1)
  println("Second Element:" + v2._2)


  // Tuple with datatype
  var v3:(String,String) = ("Hello", "Spark")
  println("v3 value:"+v3)

  // Swap the tuple position
  var v4 = v3.swap
  println("v4 value:"+v4)

}
