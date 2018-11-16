package sscf.hive.udf;/**
 * Created by lenovo on 2018/11/7.
 */

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ToDateUDF
 * @Description TODO
 * @Author lenovo
 * @Date 2018/11/7 10:52
 **/
public class ToDateUDF extends GenericUDF{
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
        return null;
    }

    public Object evaluate(DeferredObject[] args) throws HiveException {
        //有参数
        if(args != null && args.length != 0){
            //指定日志对象的格式化串
                if(args.length == 1){
                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
                try {
                    return sdf.parse((String)(args[0].get()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //两个参数,Date date,String frt
            else{
                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf.applyPattern((String)args[1].get());
                try {
                    return sdf.parse((String)args[0].get());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        //无参,返回系统时间对象
        else{
            return new Date();
        }
        return null ;
    }

    public String getDisplayString(String[] children) {
        return "toChar_xxx";
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date parse = sdf.parse("2018-12-12 11:11:11");
        System.out.println(parse);
    }
}
