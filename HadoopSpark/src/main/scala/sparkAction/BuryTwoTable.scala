package sparkAction

import java.util.Date
import javaUtil.LocalOrLine

import com.alibaba.fastjson.JSON
import conf.ConfigurationManager
import org.apache.commons.lang3.StringUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkContext, SparkConf}

import bean.StockShopClient

import scalaUtil.DateScalaUtil

/**
  * Created by lenovo on 2018/11/16.
  * 将埋点数据清洗,形成两张表
  */
object BuryTwoTable {
  private val hdfsPath: String = ConfigurationManager.getProperty("hdfs.log")
  def main(args: Array[String]): Unit = {
    val local: Boolean = LocalOrLine.judgeLocal()
    var sparkConf: SparkConf = new SparkConf().setAppName("BuryTwoTable")
    if (local){
      sparkConf=sparkConf.setMaster("local[*]")
    }
    System.setProperty("HADOOP_USER_NAME", "wangyd")
    val sc: SparkContext = new SparkContext(sparkConf)
    sc.setLogLevel("WARN")
    val sqlConf: SQLContext = new SQLContext(sc)
    val hc: HiveContext = new HiveContext(sc)
    //val file: RDD[String] = sc.textFile("C:\\Users\\lenovo\\Desktop\\bushu\\access.log",4)
    val realPath=hdfsPath+DateScalaUtil.getPreviousDateStr(-1,2)
    val file: RDD[String] = sc.textFile(realPath)
    val filterBlank: RDD[String] = file.filter(line => {
      StringUtils.isNotBlank(line)
    })
    val map: RDD[BuryLogin] = filterBlank.map(line => {
      //替换字符串
      val all: String = line.replaceAll("\\\\\"", "\"").replaceAll("\\\\\\\\u003d", "=")
      JSON.parseObject(all, classOf[BuryLogin])
    })
    val filterAction: RDD[BuryLogin] = map.filter(_.logType==2)//过滤出行为日志Data
//    val filterClient: RDD[BuryLogin] = filterAction.filter(line => {
//      val source: Int = line.source
//      if (source == 1 || source == 2 || source == 4) {
//        //过滤出客户端Data
//        true
//      } else {
//        false
//      }
//    })

   // BuryClientTable.cleanClientData(filterAction,hc)
    val filterWeb: RDD[BuryLogin] = filterAction.filter(line => {
      val source: Int = line.source
      if (source == 3) {//过滤出网页端数据
        true
      } else {
        false
      }
    })
    BuryWebTable.cleanWebData(filterWeb,hc)
  }
}


