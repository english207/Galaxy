package hzf.galaxy.remote;

import java.net.ServerSocket;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class ServerBuilder
{
    private static final int MIN_PORT = 8080;
    private static final int MAX_PORT = 65000;

    public static ServerSocket create()
    {
        ServerSocket server = null;
        for (int i = MIN_PORT; i < MAX_PORT; i++)
        {
            try
            {
                server = new ServerSocket(i);
                break;
            }
            catch (Exception e) { System.out.println(e.getMessage() + " Port is " + i); }
        }
        return server;
    }

    public static void main(String[] args) {
        ServerBuilder.create();
    }
}
