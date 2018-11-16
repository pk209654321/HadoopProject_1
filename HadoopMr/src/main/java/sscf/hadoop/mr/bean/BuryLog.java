package sscf.hadoop.mr.bean;/**
 * Created by lenovo on 2018/11/7.
 */

import java.io.Serializable;

/**
 * @ClassName BuryLog
 * @Description 埋点日志
 * @Author lenovo
 * @Date 2018/11/7 16:23
 **/
public class BuryLog implements Serializable{
    private String line;
    private String sendTime;//时间
    private Integer source;//数据来源
    private Integer logType;//

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }
}
