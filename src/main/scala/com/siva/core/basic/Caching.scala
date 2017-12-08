package com.siva.core.basic

import com.siva.common.UserConstants

/**
  * Created by Sivakumar on 12/8/2017.
  */
object Caching extends UserConstants{
  def main(args:Array[String]){
    val sc = getSparkContext("Caching/Persistence Example",LOCAL)
  }

}
