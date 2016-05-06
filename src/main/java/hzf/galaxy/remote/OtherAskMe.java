package hzf.galaxy.remote;

import hzf.galaxy.role.conpoment.RoleHandle;
import hzf.galaxy.utils.CloseSocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class OtherAskMe
{
    private ServerSocket ear;
    private boolean sleep = true;
    private ThreadPoolExecutor executor;
    private RoleHandle roleHandle;

    public OtherAskMe(ServerSocket ear, RoleHandle roleHandle)
    {
        this.ear = ear;
        this.roleHandle = roleHandle;
        init();
        executor = new ThreadPoolExecutor(5, 6 , 365 * 10, TimeUnit.DAYS, new LinkedBlockingDeque<Runnable>());
    }

    private void init()
    {
        Binaural binaural = new Binaural();
        new Thread(binaural).start();
    }

    public void go()
    {

    }

    class Binaural implements Runnable
    {
        public Binaural() { }

        @Override
        public void run()
        {
            while (sleep)
            {
                try
                {
                    Socket other = ear.accept();
                    WhatOtherSay otherSay = new WhatOtherSay(other);
                    executor.execute(otherSay);
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            System.out.println("I go to sleep");
        }
    }

    class WhatOtherSay implements Runnable
    {
        private Socket other;
        public WhatOtherSay(Socket other)
        {
            this.other = other;
        }
        @Override
        public void run()
        {
            try
            {
                BufferedReader rdr = new BufferedReader(new InputStreamReader(other.getInputStream()));
                PrintWriter wtr = new PrintWriter(other.getOutputStream());

                while (true)
                {
                    String content = rdr.readLine();
                    System.out.println("content - " + content);

                    if ("exit".equals(content))
                    {
                        sleep = false;
                        break;
                    }

                    String handleResult = roleHandle.go(content);
                    System.out.println("handleResult - " + handleResult);

                    wtr.println(handleResult);
                    wtr.flush();
                    if ("end".equals(handleResult))
                    {

                        System.out.println("Talk with other is over !");
                        break;
                    }

                }
            }
            catch (Exception e) { e.printStackTrace(); }
            finally
            {
                CloseSocket.close(other);
            }
        }
    }



}
