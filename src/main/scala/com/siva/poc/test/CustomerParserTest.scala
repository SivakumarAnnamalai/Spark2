package com.siva.poc.test

import java.io.File

import com.fasterxml.jackson.databind.ObjectMapper
import com.siva.spark.common.UserConstants

/**
  * Created by sivakumaran on 12/28/2017.
  */
object CustomerParserTest extends App with UserConstants{

  //{"id":1,"first_name":"Kaspar","last_name":"Nattrass","email":"knattrass0@loc.gov","gender":"Male","ip_address":"244.159.51.76"}
  val mapper = new ObjectMapper()
  val file = new File(POC_PATH+"customer.json")
  val customer = mapper.readValue(file,classOf[Customer])
  println(customer.toString)
}
