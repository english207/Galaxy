package hzf.galaxy.role.question;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public enum  ProcessType
{
    StrangeAskMediator("StrangeAskMediator"),         // worker 询问 中间人
    WorkerAskMediator("WorkerAskMediator"),         // worker 询问 中间人
    WorkerAskLeader("WorkerAskLeader"),             //  worker 询问 组长
    LeaderAskWorker("LeaderAskWorker"),             //  组长 询问 中间人
    LeaderAskMediator("LeaderAskMediator"),
    MediatorAskMediator("MediatorAskMediator"),         //
    MediatorAskLeader("MediatorAskLeader"),
    MediatorAskWorker("MediatorAskWorker");

    private String desc;
    ProcessType(String desc)
    {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



}
