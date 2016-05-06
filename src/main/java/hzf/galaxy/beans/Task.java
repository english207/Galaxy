package hzf.galaxy.beans;

/**
 * Created by huangzhenfeng on 2016/5/5.
 *
 */
public class Task
{
    private String taskId;
    private String taskName;
    private String meta;

    private int runNum;
    private int state;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public int getRunNum() {
        return runNum;
    }

    public void setRunNum(int runNum) {
        this.runNum = runNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
