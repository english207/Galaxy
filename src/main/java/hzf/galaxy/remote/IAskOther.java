package hzf.galaxy.remote;

import hzf.galaxy.beans.Worker;
import hzf.galaxy.role.conpoment.RoleHandle;
import hzf.galaxy.role.question.Question;
import hzf.galaxy.utils.CloseSocket;
import hzf.galaxy.utils.ContentUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class IAskOther
{
    private RoleHandle roleHandle;
    public IAskOther(RoleHandle roleHandle)
    {
        this.roleHandle = roleHandle;
    }

    private void init()
    {

    }

    public void go(String question)
    {
        WhatIask iask = new WhatIask(question);
        new Thread(iask).start();
    }

    class WhatIask implements Runnable
    {
        private Question question;
        public WhatIask(String content)
        {
            question = ContentUtils.getContentBean(content);
        }
        @Override
        public void run()
        {
            List<Worker> targets = question.getTargets();
            String content = ContentUtils.toStringContent(question.getContent());

            boolean flag = false;
            for (Worker target : targets)
            {
                String address = target.getIp();
                Integer port = target.getPort();

                Socket myself = null;
                try
                {
                    myself = new Socket(address, port);

                    BufferedReader rdr = new BufferedReader(new InputStreamReader(myself.getInputStream()));
                    PrintWriter wtr = new PrintWriter(myself.getOutputStream());

                    while (true)
                    {
                        wtr.println(content);
                        wtr.flush();

                        String reply = rdr.readLine();
                        System.out.println(reply);

                        String feekback = roleHandle.go(reply);
                        content = feekback;

                        System.out.println("feekback - " + feekback);

                        if ("end".equals(feekback))
                        {
                            wtr.println(feekback);
                            wtr.flush();
                            flag = true;
                            break;
                        }
                    }
                }
                catch (Exception e) { e.printStackTrace(); }
                finally
                {
                    CloseSocket.close(myself);
                }

                if (flag)
                {
                    System.out.println("Talk with other is over !");
                    break;
                }
            }
        }
    }

}
