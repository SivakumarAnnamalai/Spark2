package com.siva.poc.bean

import scala.beans.BeanProperty

/**
  * Created by Sivakumar on 12/24/2017.
  */
class Sinks {
  @BeanProperty
  var sinkType:String = null

  @BeanProperty
  var path:String = null

  @BeanProperty
  var delimiter:String = null
}
