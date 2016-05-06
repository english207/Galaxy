package hzf.galaxy.beans;

import hzf.galaxy.role.identify.Identify;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class Worker
{
    private String ip;
    private Integer port;
    private String nickName;
    private Identify identify;
    private String groupName;
    private Integer timeDifference = 0;         // 时差
    private Tasks tasks;
    private Long updateTime = 0l;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Identify getIdentify() {
        return identify;
    }

    public void setIdentify(Identify identify) {
        this.identify = identify;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getTimeDifference() {
        return timeDifference;
    }

    public void setTimeDifference(Integer timeDifference) {
        this.timeDifference = timeDifference;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Tasks getTasks() {
        return tasks;
    }

    public void setTasks(Tasks tasks) {
        this.tasks = tasks;
    }
}
