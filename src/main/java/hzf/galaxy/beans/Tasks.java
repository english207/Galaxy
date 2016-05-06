package hzf.galaxy.beans;

import java.util.List;

/**
 * Created by huangzhenfeng on 2016/5/6.
 *
 */
public class Tasks
{
    private List<Task> tasks;
    private Long lastTime = 0l;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }
}
