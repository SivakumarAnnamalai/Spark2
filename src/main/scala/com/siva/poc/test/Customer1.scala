package com.siva.poc.test

import scala.beans.BeanProperty

/**
  * Created by Sivakumar on 12/24/2017.
  */
class Customer1 {
  @BeanProperty var id:Int = 0;
  @BeanProperty var first_name:String = null;
  @BeanProperty var last_name:String = null;
  @BeanProperty var email:String = null;
  @BeanProperty var gender:String = null;
  @BeanProperty var ip_address:String = null;

  override def toString={
    String.format("first_name (%s), last_name (%s), email (%s)", first_name, last_name, email)
  }
}
