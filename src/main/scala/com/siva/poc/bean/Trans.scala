package com.siva.poc.bean

import scala.beans.BeanProperty

/**
  * Created by Sivakumar on 12/24/2017.
  */
class Trans {
  @BeanProperty
  var table:String = null;

  @BeanProperty
  var query:String = null;
}
