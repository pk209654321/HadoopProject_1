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
  }
}

class Student(var name:String,var age:Int){
  private var sex:Int=0
  //直接访问半生对象的私有成员
  def printCompanionObject()= println(Student.studentNo)
}

object Student{
  private var studentNo:Int=0;
  def uniqueStudentNo()={
    studentNo+=1
    studentNo
  }

  def apply(name:String,age:Int)=new Student(name,age)

  def main(args: Array[String]) {
    println(Student.uniqueStudentNo())
    val student: Student = new Student("wang",1)
    //直接访问半生类student中的私有成员
    println(student.sex)
    val student1: Student = Student("john",29)
    println(student1.name)
  }
}






















