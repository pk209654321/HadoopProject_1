package sparkAction

import java.util.Date

import bean.{StockShopWeb, StockShopClient}
import org.apache.commons.lang3.StringUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.sql.hive.HiveContext

import scalaUtil.{DateScalaUtil, StructUtil}

/**
  * Created by lenovo on 2018/11/16.
  */
object BuryWebTable {
  def cleanWebData(filterWeb: RDD[BuryLogin], hc: HiveContext): Unit = {
    val map: RDD[Row] = filterWeb.map(line => {
      val all: String = line.line
      val split: Array[String] = all.split("\\|")
      val web: StockShopWeb = new StockShopWeb
      for (i <- split) {
        val strings: Array[String] = i.split("=")
        if (strings.length > 1) {
          val trimKey: String = strings(0).trim
          val trimVal: String = strings(1).trim
          trimKey match {
            case "user_id" => if (StringUtils.isNotBlank(trimVal)) web.setUser_id(trimVal.toInt)
            case "guid" => web.setGuid(trimVal)
            case "application" => web.setApplication(trimVal)
            case "version" => web.setVersion(trimVal)
            case "platform" => web.setPlatform(trimVal)
            case "id" => web.setId(trimVal)
            case "createtime" =>if (StringUtils.isNotBlank(trimVal)) web.setCreatetime(trimVal.toLong)
            case "opentime" =>if (StringUtils.isNotBlank(trimVal)) web.setOpentime(trimVal.toLong)
            case "action_type" =>if (StringUtils.isNotBlank(trimVal)) web.setAction_type(trimVal.toInt)
            case "is_fanhui" =>if (StringUtils.isNotBlank(trimVal)) web.setIs_fanhui(trimVal.toInt)
            case "scode_id" => web.setScode_id(trimVal)
            case "market_id" => web.setMarket_id(trimVal)
            case "screen_direction" => web.setScreen_direction(trimVal)
            case "color" => web.setColor(trimVal)
            case "frameid" => web.setFrameid(trimVal)
            case "task_id" => if (StringUtils.isNotBlank(trimVal)) web.setTask_id(trimVal.toInt)
            case "qs_id" => web.setQs_id(trimVal)
            case "from_frameid" => web.setFrom_frameid(trimVal)
            case "from_object" => web.setFrom_object(trimVal)
            case "from_resourceid" => web.setFrom_resourceid(trimVal)
            case "to_frameid" => web.setTo_frameid(trimVal)
            case "to_resourceid" => web.setTo_resourceid(trimVal)
            case "to_scode" => web.setTo_scode(trimVal)
            case "order_num" => web.setOrder_num(trimVal)
            case "activity_id" => web.setActivity_id(trimVal)
            case _ =>
          }
        }
      }
      Row(web.getUser_id,
        web.getGuid,
        web.getApplication,
        web.getVersion,
        web.getPlatform,
        web.getId,
        web.getCreatetime,
        web.getOpentime,
        web.getAction_type,
        web.getIs_fanhui,
        web.getScode_id,
        web.getMarket_id,
        web.getScreen_direction,
        web.getColor,
        web.getFrameid,
        web.getTask_id,
        web.getQs_id,
        web.getFrom_frameid,
        web.getFrom_object,
        web.getFrom_resourceid,
        web.getTo_frameid,
        web.getTo_resourceid,
        web.getTo_scode,
        web.getOrder_num,
        web.getActivity_id
      )
    })
    val createDataFrame: DataFrame = hc.createDataFrame(map, StructUtil.structWeb)
    createDataFrame.show()
    //createDataFrame.registerTempTable("StockShopWeb")
    //val string: String = DateScalaUtil.date2String(new Date(), 1)
    //hc.sql(s"insert into table wangyadong.t_web_user_behavior partition(hp_stat_date='${string}') select * from StockShopWeb")
  }
}
