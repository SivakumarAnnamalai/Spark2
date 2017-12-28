package com.siva.poc.bean

import scala.beans.BeanProperty

/**
  * Created by Sivakumar on 12/24/2017.
  */
class Sample {
  @BeanProperty var source:Source = null;
  @BeanProperty var trans:Array[Trans] = null;
  @BeanProperty var sinks:Array[Sinks] = null;

  override def toString={
    String.format("Source-Path (%s), Sinks-Path (%s), Sinks-Delimiter (%s)", source.getPath, sinks(0).path, sinks(0).delimiter)
  }
}
