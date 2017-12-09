package com.siva.core.basic

import com.siva.common.UserConstants
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.DeserializationFeature

/**
  * Created by Sivakumar on 12/8/2017.
  */
object ParseJSON extends App with UserConstants {

    val sc = getSparkContext("Parse JSON Example")
    case class Person(id: Int, first_name: String,last_name:String,email:String,gender:String,ip_address:String)

    val input = sc.textFile(DATASET_PATH+"customer_data.json")
    val result = input.flatMap(record => {
        val mapper = new ObjectMapper() with ScalaObjectMapper;
        mapper.registerModule(DefaultScalaModule)
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        Some(mapper.readValue(record, classOf[Person]))
    })
    println(result.count())
    def getName(p:Person)=println(p.id+" "+p.first_name+" "+p.ip_address)
    result.take(100).foreach(getName)
}
