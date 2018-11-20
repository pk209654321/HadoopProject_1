package test

import java.io.File

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
  * Created by lenovo on 2018/11/14.
  */
object Test {
  def main(args: Array[String]): Unit = {
    val person: Person = new Person("wangyd",1)
    println(person.age)
    val string: String = JSON.toJSONString(person,SerializerFeature.PrettyFormat)
    println(string)
  }
}

class Person(var name:String,var age:Int) {
  println("constructing Person .......")
  override def toString=name+".........."+age
}

























