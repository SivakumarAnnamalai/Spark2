package com.siva.scala.collections

/**
  * Created by Sivakumar on 12/11/2017.
  */
object CreateCollection extends App{

  var v1 = Array(1,2,3)
  var v2 = Array("Hello Spark","I am better than MR")

  var v3 = List(1,2,3,4,5)
  var v4 = List("abc","xyz","mnp")

  var v5 = Set(1,3,5,2,3,5,7,8,1,3)
  var v6 = Set("abc","xyz","abc","abc")

  var v7 = Map("Hadoop"->"2.7.1","Hive"->"2.1.0")
  var v8 = Map(1->"Hortonworks",2->"Cloudera",3->"MapR")

}
