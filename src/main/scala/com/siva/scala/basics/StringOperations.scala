package com.siva.scala.basics

/**
  * Created by Sivakumar on 12/11/2017.
  */
object StringOperations extends App{

  var v1 = "Hello Spark! How are you?"

  // Split and print the contents
  println("Split operation")
  var v2 = v1.split(" ")
  v2.foreach(println)

  // Contains
  println("Checking if the variable contains 'Spark': "+v1.contains("Spark"))
  println("Checking if the variable contains 'Hadoop': "+v1.contains("Hadoop"))

  println("Lowercase/uppercase operation")
  println(v1.toLowerCase)
  println(v1.toUpperCase)

}
