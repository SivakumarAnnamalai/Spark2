package com.siva.core.basic

import com.siva.common.UserConstants

/**
  * Created by Sivakumar on 12/8/2017.
  */
object Caching extends App with UserConstants {
    val sc = getSparkContext("Caching/Persistence Example")

    // Converting the String Collection to RDD
    var l1 = Array("Hadoop","Hive","Spark","Kafka","Hadoop")
    val rdd1 = sc.makeRDD(l1)

    // import the below package
    import org.apache.spark.storage.StorageLevel

    // cache the RDD in memory
    // Data is not cached immediately.
    // Data would be cached when an action is executed on the RDD
    // cacheRDD1 and cacheRDD2 are same
    val cacheRDD1 = rdd1.cache() // val cacheRDD2 = rdd1.persist(StorageLevel.MEMORY_ONLY)

    // Below statement will get the data into cache
    // You will be able to check the cached data on the Spark UI under Storage tab
    cacheRDD1.count()

    // Removing the data from cache
    cacheRDD1.unpersist()

    // Different persistent levels
    val cacheRDD3 = rdd1.persist(StorageLevel.MEMORY_ONLY_SER)





}
