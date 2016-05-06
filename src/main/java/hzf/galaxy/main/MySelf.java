package hzf.galaxy.main;

import hzf.galaxy.data.Brain;
import hzf.galaxy.remote.IAskOther;
import hzf.galaxy.remote.OtherAskMe;
import hzf.galaxy.remote.ServerBuilder;
import hzf.galaxy.role.conpoment.RoleHandle;
import hzf.galaxy.role.identify.Identify;
import hzf.galaxy.role.question.ProcessType;
import hzf.galaxy.role.question.QuestionBuilder;

import java.net.ServerSocket;

/**
 * Created by huangzhenfeng on 2016/5/3..
 *
 */
public class MySelf
{
    private IAskOther askOther;
    private OtherAskMe askMe;
    private ServerSocket server;

    private RoleHandle roleHandle;

    public MySelf(String groupName)
    {
        init(groupName);
    }

    // 要先获得端口号，再去判断是否是中间人
    private void init(String groupName)
    {
        server = ServerBuilder.create();
        Brain.whoIam(groupName, server.getLocalPort());
        roleHandle = new RoleHandle();
        askOther = new IAskOther(roleHandle);
        askMe = new OtherAskMe(server, roleHandle);
    }

    public void askMediator()
    {
        if ( !Brain.identify.equals(Identify.Strange) )
        {
            // 询问一下Mediator
        }
        else
        {
            String content = QuestionBuilder.getQuestion(ProcessType.StrangeAskMediator);
            askOther.go(content);
        }
    }

    public void askLeader()
    {
        if ( Brain.identify.equals(Identify.Worker) )
        {
            String content = QuestionBuilder.getQuestion(ProcessType.WorkerAskLeader);
            askOther.go(content);
        }
    }

    public void askWorker()
    {
        if ( ! Brain.identify.equals(Identify.Strange) )
        {



        }
    }


    public static void main(String[] args)
    {
        MySelf mySelf = new MySelf("hzf");
        mySelf.askMediator();

        try
        {
            Thread.sleep(1000);
        }
        catch (Exception e) { e.printStackTrace(); }

        mySelf.askLeader();
    }
}
