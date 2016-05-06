package hzf.galaxy.data;

import hzf.galaxy.beans.Task;
import hzf.galaxy.beans.Tasks;
import hzf.galaxy.beans.Worker;
import hzf.galaxy.role.identify.Identify;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class Brain
{
    public static String address = "";
    public static Integer port = -1;
    public static String groupName = "hzf";
    public static String nickName = "Default";
    public static List<String> mediators = new ArrayList<String>();
    public static Map<String, String> mediatorsMap = new ConcurrentHashMap<String, String>();
    public static Identify identify = Identify.Strange;
    public static long startTime = System.currentTimeMillis();
    public static Tasks tasks;
    public static Map<String, Long> timeDifference = new ConcurrentHashMap<String, Long>();

    public static final String NEEDREPLY = "y";
    public static final String DONTREPLY = "n";

    public static Worker leader = new Worker();

    static
    {
        try
        {
            address = InetAddress.getLocalHost().getHostAddress();
            mediatorsMap.put("10.13.26.120:8080", "mediator1");
            mediators.add("10.13.26.120:8080");

            Task task = new Task();
            task.setMeta("hhh");

            tasks = new Tasks();
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public static void whoIam(String groupName, Integer port)
    {
        Brain.groupName = groupName;
        Brain.port = port;
        String addressTmp = address + ":" + port;

        for (String address : mediators)
        {
            if (addressTmp.equals(address))
            {
                identify = Identify.Mediator;
                break;
            }
        }

//        for (Map.Entry<String, String> kv : mediatorsMap.entrySet())
//        {
//            String address = kv.getKey();
//            if (addressTmp.equals(address))
//            {
//                identify = Identify.Mediator;
//                break;
//            }
//        }

        if (identify.equals(Identify.Mediator))
        {
            nickName = "mediator1";
            System.out.println("I'm a Mediator! " + addressTmp);
        }
        else
        {
            System.out.println("I'm just NoBody! So I want to question Mediator, my address is - " + addressTmp);
        }
    }

    private void init()
    {

    }

}
