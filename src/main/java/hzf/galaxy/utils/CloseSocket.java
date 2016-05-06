package hzf.galaxy.utils;

import java.net.Socket;

/**
 * Created by huangzhenfeng on 2016/5/6.
 *
 */
public class CloseSocket
{
    public static boolean close(Socket socket)
    {
        boolean flag = false;
        try
        {
            if (socket != null)
            {
                socket.close();
            }

            flag = true;
        }
        catch (Exception e) { e.printStackTrace(); }

        return flag;
    }
}
