package com.siva.spark.core.keyvalue

import com.siva.spark.common.UserConstants

/**
  * Created by Sivakumar on 12/8/2017.
  */
object TransformationsOnPairRDD extends App with UserConstants{
  val sc = getSparkContext("Create Pair RDD Example",LOCAL)

  var l1 = List((1,2),(1,4),(3,6),(4,6),(3,2))
  val pairRDD1 = sc.makeRDD(l1)
  // reduceByKey is one of the transformation
  // This is used to aggregate the values wherever the key is same
  // This function works with 2 elements at a time
  // This applied the function on the map side as well as on the reduce side(equivalent to Combiner in MR)
  val reduceByKeyRDD = pairRDD1.reduceByKey((x,y)=>x+y)
  printOutput(reduceByKeyRDD,"ReduceByKey RDD")


  var l2 = Array(("Hive","2.1.0"),("Hadoop","2.7.1"),("Spark","2.2.0"),("HBase","1.2.1"))
  val pairRDD2 = sc.parallelize(l2)
  // sortByKey is one of the transformation
  // this is used to sort the RDD by key
  // Sorting order can be in ascending or descending
  val sortByKeyRDD1 = pairRDD2.sortByKey(ascending = true)
  printOutput(sortByKeyRDD1,"SortByKey RDD1")

  // Example of Descending order
  val sortByKeyRDD2 = pairRDD2.sortByKey(ascending = false)
  printOutput(sortByKeyRDD2,"SortByKey RDD2")

  var l3 = List((1,2),(1,4),(3,6),(4,6),(3,2),(1,5))
  val pairRDD3 = sc.makeRDD(l3)
  // reduceByKey is one of the transformation
  // This is used to aggregate the values wherever the key is same
  // This function works with 2 elements at a time
  // This applied the function on the map side as well as on the reduce side(equivalent to Combiner in MR)
  val groupByKeyRDD = pairRDD3.groupByKey()
  printOutput(groupByKeyRDD,"GroupByKey RDD")


}
