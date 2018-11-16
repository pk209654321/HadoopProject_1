package test

import java.io.File

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by lenovo on 2018/11/14.
  */
object Test {
  def main(args: Array[String]): Unit = {
    val person: Person = new Person("john",29)
    println(person)
  }
}

class Person(val name:String,val age:Int){
  println("constructing Person .......")
  override def toString=name+".........."+age
}

























