package hzf.galaxy.role.question;

import hzf.galaxy.beans.Worker;
import hzf.galaxy.data.Brain;
import hzf.galaxy.role.identify.Identify;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class QuestionBuilder
{
    private static ObjectMapper jackSon = new ObjectMapper();

    private QuestionBuilder() {}

    public void init()
    {

    }

    public static String getQuestion(ProcessType processType)
    {
        String content = null;
        try
        {
            content = jackSon.writeValueAsString(get(processType));
        }
        catch (Exception e) { e.printStackTrace(); }

        System.out.println("QuestionBuilder - content is " + content);

        return content;
    }

    private static Question get(ProcessType processType)
    {
        if (Brain.identify.equals(Identify.Strange))
        {
            return sayHelloToMediator();
        }

        if (processType.equals(ProcessType.WorkerAskMediator))
        {
            return workerAskMediator();
        }

        if (processType.equals(ProcessType.WorkerAskLeader))
        {
            return workerAskLeader();
        }


//        if (Brain.identify.equals(Identify.Worker))     // 提出worker对mediator或者leader的提问
//        {
//            if (processType.equals(ProcessType.WorkerAskMediator))
//            {
//                return workerAskMediator();
//            }
//
//            if (processType.equals(ProcessType.WorkerAskLeader))
//            {
//                return workerAskLeader();
//            }
//        }
//
//        if (Brain.identify.equals(Identify.Mediator))   // 提出Mediator对leader的提问
//        {
//            if (processType.equals(ProcessType.MediatorAskMediator))
//            {
//                return mediatorAskMediator();
//            }
//
//            if (processType.equals(ProcessType.MediatorAskLeader))
//            {
//                return mediatorAskLeader();
//            }
//
//            if (processType.equals(ProcessType.MediatorAskWorker))
//            {
//                return mediatorAskWorker();
//            }
//
//            return sayHelloToMediator();
//        }

        return null;
    }

    private static Question workerAskLeader()
    {
        Question question = new Question();

        List<Worker> leaders =  new ArrayList<Worker>();
        leaders.add(Brain.leader);

        question.setFromWho(getMySelfWork());
        question.setTargets(leaders);
        question.setProcessType(ProcessType.WorkerAskLeader);
        question.setContent(workerAskLeaderWhat());

        return question;
    }

    private static Question workerAskMediator()
    {
        Question question = new Question();

        Worker me = getMySelfWork();
        List<Worker> mediators =  getMediator();

        question.setFromWho(me);
        question.setTargets(mediators);
        question.setProcessType(ProcessType.WorkerAskMediator);

        question.setContent(workerAskMediatorWhat());

        return null;
    }

    private static Question mediatorAskMediator()
    {
        return null;
    }

    private static Question mediatorAskLeader()
    {
        return null;
    }

    private static Question mediatorAskWorker()
    {
        return null;
    }

    private static Worker getMySelfWork()
    {
        Worker me = new Worker();

        me.setIp(Brain.address);
        me.setPort(Brain.port);
        me.setNickName(Brain.nickName);
        me.setIdentify(Brain.identify);
        me.setGroupName(Brain.groupName);
        me.setTasks(Brain.tasks);

        return me;
    }

    private static List<Worker> getMediator()
    {
        List<Worker> mediators = new ArrayList<Worker>();
        List<String> addresses = Brain.mediators;

        for (String address : addresses)
        {
            Worker mediator = new Worker();
            String[] ipAndPort = address.split(":");

            mediator.setIp(ipAndPort[0]);
            mediator.setPort(Integer.parseInt(ipAndPort[1]));
            mediator.setIdentify(Brain.identify);

            mediators.add(mediator);
        }
        return mediators;
    }

    //  仅限于
    private static Map<String, Object> workerAskMediatorWhat()
    {
        Map<String, Object> map = new HashMap<String, Object>();

        if (Brain.identify.equals(Identify.Worker))
        {
            map.put("questionType", QuestionType.LTNS);
            // todo 相应的数据
        }
        return map;
    }

    //  仅限于worker Ask Leader
    private static Map<String, Object> workerAskLeaderWhat()
    {
        Map<String, Object> map = new HashMap<String, Object>();

        if (Brain.identify.equals(Identify.Worker))
        {
            map.put("questionType", QuestionType.Hello);
            map.put("sponsor", Brain.nickName);

            map.put("groupName", Brain.groupName);
            map.put("startTime", Brain.startTime);
            map.put("address", Brain.address);
            map.put("port", Brain.port);
            map.put("needReply", Brain.NEEDREPLY);

            // todo 相应的数据
        }
        return map;
    }

    private static Question sayHelloToMediator()
    {
        Question question = new Question();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("questionType", QuestionType.Hello);
        map.put("groupName", Brain.groupName);
        map.put("startTime", Brain.startTime);
        map.put("address", Brain.address);
        map.put("port", Brain.port);
        map.put("needReply", Brain.NEEDREPLY);

        question.setFromWho(getMySelfWork());
        question.setTargets(getMediator());
        question.setProcessType(ProcessType.StrangeAskMediator);
        question.setContent(map);

        return question;
    }

}
