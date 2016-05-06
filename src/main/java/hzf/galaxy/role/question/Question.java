package hzf.galaxy.role.question;

import hzf.galaxy.beans.Worker;

import java.util.List;
import java.util.Map;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class Question
{
    private Worker fromWho;
    private List<Worker> targets;

    private ProcessType processType;

    private Map<String, Object> content;

    public Worker getFromWho() {
        return fromWho;
    }

    public void setFromWho(Worker fromWho) {
        this.fromWho = fromWho;
    }

    public List<Worker> getTargets() {
        return targets;
    }

    public void setTargets(List<Worker> targets) {
        this.targets = targets;
    }

    public ProcessType getProcessType() {
        return processType;
    }

    public void setProcessType(ProcessType processType) {
        this.processType = processType;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }
}
