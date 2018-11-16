package sscf.hadoop.mr.pclog;/**
 * Created by lenovo on 2018/11/7.
 */

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @ClassName MyFileOutputFormat
 * @Description TODO
 * @Author lenovo
 * @Date 2018/11/7 17:04
 **/
public class MyFileOutputFormat extends FileOutputFormat<Text, NullWritable> {
    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        FileSystem fs = FileSystem.get(context.getConfiguration());
        Path path = new Path("E:\\local_mr_run_data\\output\\tt.log");
        FSDataOutputStream enhanceOut = fs.create(path);
        return new MyRecordWriter(enhanceOut);
    }
    static class MyRecordWriter extends RecordWriter<Text, NullWritable>{
        FSDataOutputStream enhanceOut = null;
        public MyRecordWriter(FSDataOutputStream enhanceOut) {
            this.enhanceOut = enhanceOut;
        }
        @Override
        public void write(Text key, NullWritable value) throws IOException, InterruptedException {
            //有了数据，你来负责写到目的地  —— hdfs
            //判断，进来内容如果是带tocrawl的，就往待爬清单输出流中写 toCrawlOut
            String s = key.toString() + "\n";
            enhanceOut.write(s.getBytes());
        }
        @Override
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            if(enhanceOut!=null){
                enhanceOut.close();
            }
        }
    }

}
