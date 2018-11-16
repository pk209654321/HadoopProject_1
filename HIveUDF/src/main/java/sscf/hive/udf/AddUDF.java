package sscf.hive.udf;/**
 * Created by lenovo on 2018/11/7.
 */

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * @ClassName AddUDF
 * @Description TODO
 * @Author lenovo
 * @Date 2018/11/7 9:02
 **/
public class AddUDF extends UDF{
   public String evaluate(String string){
       if (StringUtils.isNotEmpty(string)){
           return "hello"+string;
       }else{
           return "";
       }
   }

}
