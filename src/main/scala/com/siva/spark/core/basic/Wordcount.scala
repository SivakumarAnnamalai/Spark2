package com.siva.spark.core.basic

import com.siva.spark.common.UserConstants
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Sivakumar on 12/8/2017.
  */
object Wordcount extends UserConstants{
  def main(args:Array[String]){

    println("Hello Spark")
    val conf = new SparkConf()
    conf.setMaster(LOCAL)
    conf.setAppName("Wordcount")

    val sc = new SparkContext(conf)
    val rdd1 = sc.textFile(DATASET_PATH + "test.txt")  // output would be RDD[abc xyz abc,mnp abc xyz]
    val rdd2 = rdd1.flatMap(x=>x.split(" "))           // output would be RDD[abc,xyz,abc,mnp,abc,xyz]
    val rdd3 = rdd2.map(x=>(x,1))                      // output would be RDD[(abc,1),(xyz,1),(abc,1),(mnp,1),(abc,1),(xyz,1)]
    val rdd4 = rdd3.reduceByKey((x,y)=>x+y)            // output would be RDD[(abc,3),(xyz,2),(mnp,1)]
    rdd4.foreach(println)
  }
}
