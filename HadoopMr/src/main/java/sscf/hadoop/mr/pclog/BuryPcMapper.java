package sscf.hadoop.mr.pclog;/**
 * Created by lenovo on 2018/11/7.
 */

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import sscf.hadoop.mr.bean.BuryLog;

import java.io.IOException;

/**
 * @ClassName BuryPcMapper
 * @Description TODO
 * @Author lenovo
 * @Date 2018/11/7 16:50
 **/
public class BuryPcMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text keyOut = new Text();
        IntWritable valueOut = new IntWritable();
        String lineStr = value.toString();
        if(StringUtils.isBlank(lineStr)){//判断是否为空
            return;
        }
        lineStr = lineStr.replaceAll("\\\\\"", "\"").replaceAll("\\\\\\\\u003d", "=");
        BuryLog buryLog = JSON.parseObject(lineStr, BuryLog.class);
        String line = buryLog.getLine();
        Integer logType = buryLog.getLogType();
        Integer source = buryLog.getSource();
        //解析line
        String[] split = line.split("\\|");
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<split.length;i++){
            String one = split[i];
            String[]  t= one.split("=");
            if(t.length==2){
                String keyEq = t[0];
                String valEq = t[1].trim();
                if (StringUtils.isBlank(valEq)){
                    valEq="is_null";
                }
                if (i==split.length-1){
                    sb.append(valEq);
                }
                sb.append(valEq).append("\t");
            }else{
                return;//当前数据格式不对
            }
        }
        keyOut.set(sb.toString());
        context.write(keyOut,NullWritable.get());
    }
}
