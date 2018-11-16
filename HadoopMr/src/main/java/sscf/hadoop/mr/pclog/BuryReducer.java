package sscf.hadoop.mr.pclog;/**
 * Created by lenovo on 2018/11/7.
 */

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import sscf.hadoop.mr.demo.Util;

import java.io.IOException;

/**
 * @ClassName BuryReducer
 * @Description TODO
 * @Author lenovo
 * @Date 2018/11/7 17:43
 **/
public class BuryReducer  extends Reducer<Text, NullWritable, Text, NullWritable> {
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        context.write(key,NullWritable.get());
    }
}
