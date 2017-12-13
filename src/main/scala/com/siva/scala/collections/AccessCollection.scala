package com.siva.scala.collections

/**
  * Created by sivakumaran on 12/11/2017.
  */
object AccessCollection extends App{

  var v1 = Array(1,2,3)
  var v2 = Array("Hello Spark","I am better than MR")
  println(v1(0)) // Access First element of array. Index starts from 0
  println(v2(1)) // You have to use paranthesis and its not square bracket

  var v3 = List(1,2,3,4,5)
  var v4 = List("abc","xyz","mnp")
  println(v3(3))
  println(v4(2))

  var v5 = Set(1,3,5,2,3,5,7,8,1,3)
  var v6 = Set("abc","xyz","abc","abc")

}
