package hzf.galaxy.role.conpoment;

import hzf.galaxy.beans.Worker;
import hzf.galaxy.data.Brain;
import hzf.galaxy.role.base.RoleDomeSomething;
import hzf.galaxy.role.question.QuestionType;
import hzf.galaxy.utils.ContentUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class IMLeader extends IMBase implements RoleDomeSomething
{
    private Map<String, Worker> same_group = new ConcurrentHashMap<String, Worker>();

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

        // 若是对方第一次访问则告诉对方队长信息，还有昵称
        if (questionType.equals(QuestionType.LTNS.getType()))
        {
            System.out.println("questionType is " + questionType);
            return ltns(map);
        }

        return null;
    }

    public String welCome(Map<String, Object> other)
    {
        String nickName = (String) other.get("nickName");
        String groupName = (String) other.get("groupName");
        String address = (String) other.get("address");
        Integer port = (Integer) other.get("port");
        Long starTime = (Long) other.get("startTime");

        Long timeDifference = System.currentTimeMillis() - starTime;

        Worker newWorker = new Worker();
        newWorker.setIp(address);
        newWorker.setPort(port);
        newWorker.setGroupName(groupName);
        newWorker.setNickName(nickName);

        if ( (nickName != null && nickName.equals("") ) )
        {
            if ( same_group.get(nickName) == null )
            {
                same_group.put(nickName, newWorker);
            }
        }

        List<Worker> list = new ArrayList<Worker>();

        for (Map.Entry<String, Worker> same_worker : same_group.entrySet())
        {
            list.add(same_worker.getValue());
        }

        Map<String, Object> reply = new HashMap<String, Object>();

        reply.put("sponsor", nickName);
        reply.put("timeDifference", timeDifference);
        reply.put("fromAddressIP", Brain.address);
        reply.put("fromAddressPort", Brain.port);
        reply.put("same_group", list);

        return ContentUtils.toStringContent(reply);
    }

    @Override
    public String ltns(Map<String, Object> other)
    {
        return null;
    }

}
