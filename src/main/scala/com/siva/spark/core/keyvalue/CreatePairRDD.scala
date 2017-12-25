package com.siva.spark.core.keyvalue

import com.siva.spark.common.UserConstants

/**
  * Created by Sivakumar on 12/8/2017.
  */
object CreatePairRDD extends App with UserConstants{

  val sc = getSparkContext("Create Pair RDD Example",LOCAL)

  // method1 - convert normal collection of key/value pair into Pair RDD using makeRDD method
  var l1 = List((1,2),(3,4),(5,6))
  val pairRDD1 = sc.makeRDD(l1)
  printOutput(pairRDD1,"Pair RDD1")

  // method2 - convert normal collection of key/value pair into Pair RDD using parallelize method
  var l2 = Array(("Hadoop","2.7.1"),("Hive","2.1.0"),("Spark","2.2.0"),("HBase","1.2.1"))
  val pairRDD2 = sc.parallelize(l2)
  printOutput(pairRDD2,"Pair RDD2")

  // method2 - convert normal collection of key/value pair into Pair RDD using parallelize method
  var l3 = Array(("Hadoop",2.7),("Hive",2.1),("Spark",2.2),("HBase",1.2))
  val pairRDD3 = sc.makeRDD(l3)
  printOutput(pairRDD3,"Pair RDD3")

  // method3 - reading the local file into RDD using textFile method
  var rdd = sc.textFile(DATASET_PATH+"test.txt")
  val pairRDD4 = rdd.flatMap(x=>x.split(" ")).map(x=>(x,1))
  printOutput(pairRDD4,"Pair RDD4")

}

