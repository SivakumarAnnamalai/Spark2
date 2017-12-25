package com.siva.poc.test

import java.io.File

import com.siva.poc.bean.Sample
import com.siva.spark.common.UserConstants
import org.codehaus.jackson.map.ObjectMapper

/**
  * Created by sivakumaran on 12/24/2017.
  */
object JSONParserTest extends App with UserConstants {

  //{"id":1,"first_name":"Kaspar","last_name":"Nattrass","email":"knattrass0@loc.gov","gender":"Male","ip_address":"244.159.51.76"}
  /*val mapper = new ObjectMapper()
  val file = new File(POC_PATH+"customer.json")
  val customer = mapper.readValue(file,classOf[Customer1])
  println(customer.toString)*/

  val mapper = new ObjectMapper()
  val file = new File(POC_PATH+"sample.json")
  val sample = mapper.readValue(file,classOf[Sample])
  println(sample.toString)

  val sc = getSparkContext("Test")
  val r1 = sc.textFile(sample.source.getPath)
  val r2 = r1.flatMap(_.split(" "))
  r2.saveAsTextFile(sample.sinks(0).path)


}
