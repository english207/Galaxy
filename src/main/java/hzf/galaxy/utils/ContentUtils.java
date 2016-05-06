package hzf.galaxy.utils;

import hzf.galaxy.beans.Task;
import hzf.galaxy.beans.Tasks;
import hzf.galaxy.beans.Worker;
import hzf.galaxy.role.identify.Identify;
import hzf.galaxy.role.question.Question;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huangzhenfeng on 2016/5/4.
 *
 */
public class ContentUtils
{
    private static ObjectMapper jackSon = new ObjectMapper();

    public static Map<String, Object> getContentMap(String content)
    {
        Map<String, Object> map = null;
        try
        {
            map = jackSon.readValue(content, Map.class);
        }
        catch (Exception e) { e.printStackTrace(); }
        return map;
    }

    public static Question getContentBean(String content)
    {
        Question question = null;
        try
        {
            question = jackSon.readValue(content, Question.class);
        }
        catch (Exception e) { e.printStackTrace(); }
        return question;
    }

    public static String toStringContent(Map<String, Object> contentMap)
    {
        String content = null;
        try
        {
            content = jackSon.writeValueAsString(contentMap);
        }
        catch (Exception e) { e.printStackTrace(); }

        return content;
    }

    public static Identify getIdentify(String identifyTmp)
    {
        Identify identify = null;
        if (Identify.Leader.getDesc().equals(identifyTmp))
        {
            identify = Identify.Leader;
        }
        else if (Identify.Mediator.getDesc().equals(identifyTmp))
        {
            identify = Identify.Mediator;
        }
        else if (Identify.Worker.getDesc().equals(identifyTmp))
        {
            identify = Identify.Worker;
        }
        else if (Identify.Strange.getDesc().equals(identifyTmp))
        {
            identify = Identify.Strange;
        }
        return identify;
    }

    public static List<Task> getTasks(String content)
    {

        return null;
    }


    public static Worker getWorker(Map<String, Object> map)
    {
        Worker worker = null;

        try
        {
            worker = new Worker();
            String address = (String) map.get("ip");
            Integer port = (Integer) map.get("port");
            String nickName = (String) map.get("nickName");
            String groupName = (String) map.get("groupName");
            String lastTime = (String) map.get("lastTime");
            Identify identify = ContentUtils.getIdentify((String) map.get("identify"));

            List<Map<String, Object>> list  = (List<Map<String, Object>>) map.get("tasks");
            List<Task> taskList = null;
            Tasks tasks = null;
            if (list != null)
            {
                tasks = new Tasks();
                taskList = new ArrayList<Task>();
                for (Map<String, Object> taskTmp : list)
                {
                    String taskId = (String) taskTmp.get("taskId");
                    String taskName = (String) taskTmp.get("taskName");
                    String meta = (String) taskTmp.get("meta");
                    Integer runNum = (Integer) taskTmp.get("runNum");
                    Integer state = (Integer) taskTmp.get("state");

                    Task task = new Task();
                    task.setTaskId(taskId);
                    task.setTaskName(taskName);
                    task.setMeta(meta);
                    task.setRunNum(runNum);
                    task.setState(state);

                    taskList.add(task);
                }
                tasks.setTasks(taskList);
                tasks.setLastTime(Long.parseLong(lastTime));
            }

            // 设置本地Worker
            worker.setIp(address);
            worker.setPort(port);
            worker.setNickName(nickName);
            worker.setGroupName(groupName);
            worker.setIdentify(identify);
            worker.setTasks(tasks);
//            worker.setTasks(task);
        }
        catch (Exception e) { e.printStackTrace(); }

        return worker;
    }

    private static String json = "{\"leader\":{\"ip\":\"10.13.26.120\",\"port\":8081,\"nickName\":\"hzf1462442462355\",\"identify\":\"Leader\",\"groupName\":\"hzf\",\"timeDifference\":0,\"tasks\":[{\"meta\":\"hhhs\"},{\"meta\":\"hhhs\"}],\"updateTime\":0},\"fromAddressPort\":8080,\"newWorker\":{\"ip\":\"10.13.26.120\",\"port\":8081,\"nickName\":\"hzf1462442462355\",\"identify\":\"Leader\",\"groupName\":\"hzf\",\"timeDifference\":0,\"tasks\":null,\"updateTime\":0},\"fromAddressIP\":\"10.13.26.120\",\"timeDifference\":489}";

    public static void main(String[] args) {
        Map<String, Object> map = ContentUtils.getContentMap(json);

        Map<String, Object> workerMap = (Map<String, Object>) map.get("leader");

        try
        {
            List<Map<String, Object>> list  = (List<Map<String, Object>>) workerMap.get("tasks");
            ContentUtils.getContentMap((String) workerMap.get("tasks"));
        }
        catch (Exception e) { e.printStackTrace(); }
        System.out.println(workerMap.get("tasks"));

        workerMap.size();
    }

}
