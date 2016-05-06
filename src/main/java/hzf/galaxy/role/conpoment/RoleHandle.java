package hzf.galaxy.role.conpoment;

import hzf.galaxy.data.Brain;
import hzf.galaxy.role.identify.Identify;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class RoleHandle
{
    private IMMediator mediator;
    private IMLeader leader;
    private IMWorker worker;
    private IMStrange strange;

    public RoleHandle()
    {
        init();
    }

    private void init()
    {
        mediator = new IMMediator();
        leader = new IMLeader();
        worker = new IMWorker();
        strange = new IMStrange();
    }

    public synchronized String go(String content)
    {
        String feekback = null;

        if ("end".equals(content) || "exit".equals(content))
        {
            return content;
        }

        //Map<String, Object> map = ContentUtils.getContentMap(content);

        if (Brain.identify.equals(Identify.Mediator))
        {
            feekback = mediator.go(content);
        }
        else if (Brain.identify.equals(Identify.Leader))
        {
            feekback = leader.go(content);
        }
        else if (Brain.identify.equals(Identify.Worker))
        {
            feekback = worker.go(content);
        }
        else if (Brain.identify.equals(Identify.Strange))
        {
            feekback = strange.go(content);
        }

        return feekback;
    }

}
