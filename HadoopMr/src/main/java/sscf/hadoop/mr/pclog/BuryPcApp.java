package sscf.hadoop.mr.pclog;/**
 * Created by lenovo on 2018/11/7.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.File;

/**
 * @ClassName BuryPcApp
 * @Description TODO
 * @Author lenovo
 * @Date 2018/11/7 17:20
 **/
public class BuryPcApp {

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
         //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public static void main(String[] args) throws Exception {
        deleteDir(new File("E:\\local_mr_run_data\\output"));
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //设置job的各种属性
        job.setJobName("BuryPcApp");                        //作业名称
        job.setJarByClass(BuryPcApp.class);                 //搜索类
        job.setInputFormatClass(TextInputFormat.class); //设置输入格式
        job.setOutputFormatClass(MyFileOutputFormat.class);
        //添加输入路径
        //FileInputFormat.addInputPath(job,new Path(args[0]));
        FileInputFormat.addInputPath(job, new Path("E:\\local_mr_run_data\\input"));
        //设置输出路径
        //FileOutputFormat.setOutputPath(job,new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path("E:\\local_mr_run_data\\output"));

        //设置分区类
        //job.setPartitionerClass(MyPartitioner.class);   //设置自定义分区

        //设置合成类
        //job.setCombinerClass(WCReducer.class);          //设置combiner类

        job.setMapperClass(BuryPcMapper.class);             //mapper类
        job.setReducerClass(BuryReducer.class);           //reducer类

        job.setNumReduceTasks(1);                       //reduce个数

        job.setMapOutputKeyClass(Text.class);           //
        job.setMapOutputValueClass(NullWritable.class);  //

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);     //
        job.waitForCompletion(true);
    }
}
