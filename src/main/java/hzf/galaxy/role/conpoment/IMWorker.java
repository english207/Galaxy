package hzf.galaxy.role.conpoment;

import hzf.galaxy.role.base.RoleDomeSomething;

import java.util.Map;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class IMWorker extends IMBase implements RoleDomeSomething
{

    @Override
    public String go(String content)
    {

        Map<String, Object> map = getContent(content);

//        String questionType = (String) map.get("questionType");
//
//        // 若是对方第一次访问则告诉对方队长信息，还有昵称
//        if (questionType.equals(QuestionType.Hello.getType()))
//        {
//            System.out.println("questionType is " + questionType);
//            return welCome(map);
//        }
//        else if (questionType.equals(QuestionType.LTNS.getType()))
//        {
//            System.out.println("processType is " + questionType);
//        }
//
//        System.out.println("IMMediator size is " + map.size());
//        return "What can I do for you";
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
