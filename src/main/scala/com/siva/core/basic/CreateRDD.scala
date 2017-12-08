package com.siva.core.basic

import com.siva.common.UserConstants
import com.siva.core.basic.Caching.{LOCAL, getSparkContext}

/**
  * Created by Sivakumar on 12/8/2017.
  */
object CreateRDD extends UserConstants{
  def main(args:Array[String]){
    val sc = getSparkContext("Create RDD Example",LOCAL)

    // method1 - convert normal collection into RDD using makeRDD method
    var l1 = List(1 to 100)
    val rdd1 = sc.makeRDD(l1)

    // method2 - convert normal collection into RDD using parallelize method
    var l2 = List(1 to 500)
    val rdd2 = sc.parallelize(l2)

    // method3 - reading the local file into RDD using textFile method
    var rdd3 = sc.textFile(DATASET_PATH+"test.txt")

    // method3 - reading the HDFS file into RDD using textFile method
    var rdd4 = sc.textFile(DATASET_PATH+"test.txt")


  }

}
