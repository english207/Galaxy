package hzf.galaxy.role.conpoment;

import hzf.galaxy.beans.Worker;
import hzf.galaxy.data.Brain;
import hzf.galaxy.role.base.RoleDomeSomething;
import hzf.galaxy.utils.ContentUtils;

import java.util.Map;

/**
 * Created by huangzhenfeng on 2016/5/5.
 *
 */
public class IMStrange extends IMBase implements RoleDomeSomething
{

    @SuppressWarnings("unchecked")
    @Override
    public String go(String content)
    {
        Map<String, Object> map = getContent(content);

        Map<String, Object> workerMap = (Map<String, Object>) map.get("newWorker");
        Map<String, Object> leaderMap = (Map<String, Object>) map.get("leader");

        String timeDifference =  map.get("timeDifference") + "";
        String fromAddressIP = (String) map.get("fromAddressIP");
        Integer fromAddressPort = (Integer) map.get("fromAddressPort");

        // 设置自己
        Worker myself = ContentUtils.getWorker(workerMap);
        // 设置leader
        Worker leader = ContentUtils.getWorker(leaderMap);

        // 设置本机与目标机器的时间差
        Brain.timeDifference.put(fromAddressIP + ":" + fromAddressPort, Long.parseLong(timeDifference));
        Brain.identify = myself.getIdentify();
        Brain.nickName = myself.getNickName();
        Brain.leader = leader;

        System.out.println(myself.getNickName());

        return "end";
    }

    @Override
    public String welCome(Map<String, Object> other)
    {
        return null;
    }

    @Override
    public String ltns(Map<String, Object> other) {
        return null;
    }
}
