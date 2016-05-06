package hzf.galaxy.role.conpoment;

import hzf.galaxy.beans.Worker;
import hzf.galaxy.data.Brain;
import hzf.galaxy.role.base.RoleDomeSomething;
import hzf.galaxy.role.identify.Identify;
import hzf.galaxy.role.question.QuestionType;
import hzf.galaxy.utils.ContentUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 *  专门处理别人的问题或者我该怎么回答
 *
 */
public class IMMediator extends IMBase implements RoleDomeSomething
{
    // oldWorker用作于定时扫描一下worker的startTime,updateTime,超时则代表已经下线了
    private Map<String, Map<String, Worker>> oldWorkers = new ConcurrentHashMap<String, Map<String, Worker>>();
    private Map<String, Map<String, Worker>> newWorkers = new ConcurrentHashMap<String, Map<String, Worker>>();

    private Map<String, Worker> leaderMap = new ConcurrentHashMap<String, Worker>();

    private Map<String,Long> updateMap = new ConcurrentHashMap<String, Long>();

    @Override
    public String go(String content)
    {
        Map<String, Object> map = getContent(content);

        String questionType = (String) map.get("questionType");

        // 若是对方第一次访问则告诉对方队长信息，还有昵称
        if (questionType.equals(QuestionType.Hello.getType()))
        {
            System.out.println("questionType is " + questionType);
            return welCome(map);
        }
        else if (questionType.equals(QuestionType.LTNS.getType()))
        {
            System.out.println("processType is " + questionType);
        }

        System.out.println("IMMediator size is " + map.size());
//        return "What can I do for you";
        return "end";
    }

    public String welCome(Map<String, Object> other)
    {
        String groupName = (String) other.get("groupName");
        String address = (String) other.get("address");
        Integer port = (Integer) other.get("port");
        Long starTime = (Long) other.get("startTime");

        Long timeDifference = System.currentTimeMillis() - starTime;

        Map<String, Object> reply = new HashMap<String, Object>();

        String nickName = groupName + System.currentTimeMillis();
        Map<String, Worker> map = oldWorkers.get(groupName);

        Identify identify = Identify.Worker;

        // 查看本地 map 是否有对应的worker
        if (map == null || map.size() == 0)
        {
            map = new ConcurrentHashMap<String, Worker>();
            oldWorkers.put(groupName, map);
            identify = Identify.Leader;
        }

        Worker newWorker = new Worker();
        newWorker.setIp(address);
        newWorker.setPort(port);
        newWorker.setGroupName(groupName);
        newWorker.setNickName(nickName);
        newWorker.setIdentify(identify);

        while ( ! map.containsKey(nickName) )
        {
            try
            {
                Thread.sleep(1);
            }
            catch (Exception e) { e.printStackTrace(); }
            nickName = groupName + System.currentTimeMillis();
            map.put(nickName, newWorker);
        }

        newWorker.setNickName(nickName);

        if (identify.equals(Identify.Leader))
        {
            leaderMap.put(groupName, newWorker);
        }

        // 将昵称以及报到时间存储在本地，定期扫描
        updateMap.put(nickName, starTime);

        Worker leadr = leaderMap.get(groupName);

        reply.put("newWorker", newWorker);
        reply.put("leader", leadr);
        reply.put("timeDifference", timeDifference);
        reply.put("fromAddressIP", Brain.address);
        reply.put("fromAddressPort", Brain.port);

        return ContentUtils.toStringContent(reply);
    }

    public String ltns(Map<String, Object> other)
    {
        String groupName = (String) other.get("groupName");
        String address = (String) other.get("address");
        Integer port = (Integer) other.get("port");
        Long starTime = (Long) other.get("startTime");

        Map<String, Object> reply = new HashMap<String, Object>();
        return ContentUtils.toStringContent(reply);
    }

    public boolean check(String groupName, String nickName)
    {
        boolean flag = false;
        Worker worker = oldWorkers.get(groupName).get(nickName);

        if (worker != null)
        {
            flag = true;
        }

        return flag;
    }





}
