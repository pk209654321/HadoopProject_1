package testFunction

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}

import scala.collection.mutable

/**
  * Created by lenovo on 2018/11/26.
  */
object TestDemo {
  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf().setAppName("TestDemo").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val accessData: RDD[String] = sc.textFile("hdfs://leader:8020/ip/access.log")
    val ipRdd: RDD[String] = sc.textFile("hdfs://leader:8020/ip/ip.txt")
    val ipT = ipRdd.map(line => {
      val split: Array[String] = line.split("\\|")
      val startNum = split(2).toLong
      val endNum = split(3).toLong
      val province = split(6)
      (startNum, endNum, province)
    })
    val toBuffer: Array[(Long, Long, String)] = ipT.collect()
    val broadcast = sc.broadcast(toBuffer)
    val value: Array[(Long, Long, String)] = broadcast.value
    val map: RDD[Long] = accessData.map(line => {
      val split = line.split("\\|")
      val ip = split(1)
      ip2Long(ip)
    })

    val function: Int =searchFunction2(value,ip2Long("122.224.153.234"))
    println(value(function)._3)
  }


  //将IP地址转换成long值
  def ip2Long(ip: String): Long = {
    val fragments = ip.split("[.]")
    var ipNum = 0L
    for (i <- 0 until fragments.length){
      ipNum =  fragments(i).toLong | ipNum << 8L
    }
    ipNum
  }

  def binarySearch(lines: Array[(Long, Long, String)], ip: Long) : Int = {
    var low = 0
    var high = lines.length - 1
    while (low <= high) {
      val middle = (low + high) / 2
      if ((ip >= lines(middle)._1) && (ip <= lines(middle)._2))
         middle
      if (ip < lines(middle)._1)
        high = middle - 1
      else {
        low = middle + 1
      }
    }
    -1
  }
  //折半查找
  def searchFunction2(array:Array[(Long,Long,String)],ipLong:Long): Int ={
    var low=0
    var high=array.length-1
    while(low<=high){
      val middle=(low+high)/2
      if(ipLong>=array(middle)._1 && ipLong<=array(middle)._2){
        return middle
      }else if(ipLong<array(middle)._1){
        high=middle-1
      }else{
        low=middle+1
      }
    }
    -1
  }

}
