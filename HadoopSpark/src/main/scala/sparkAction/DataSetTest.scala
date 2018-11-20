package sparkAction

import org.apache.spark.sql.{SQLContext, DataFrame, Dataset}
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.hive.HiveContext

/**
  * Created by lenovo on 2018/11/20.
  */
object DataSetTest {
  def main(args: Array[String]) {
    val conf: SparkConf = new SparkConf().setAppName("DataSetTest").setMaster("local[*]")
    val context: SparkContext = new SparkContext(conf)
    val hc: HiveContext = new HiveContext(context)
    val sqlContext: SQLContext = new SQLContext(context)
  }
}
