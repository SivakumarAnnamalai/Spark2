package com.siva.scala.basics

import org.apache.spark.{SparkConf, SparkContext}

object HelloWorld extends App{
  println("Hello World")

  val conf = new SparkConf()
  conf.setAppName("Test")
  conf.setMaster("local")

  val sc = new SparkContext(conf)
}

/*
object HelloWorld{
  def main(args:Array[String]) {
    println("Hello World")
  }
}
*/
