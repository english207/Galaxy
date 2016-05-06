package hzf.galaxy.remote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class Test
{
    public static void main(String[] args) throws Exception
    {
        Socket server = new Socket(InetAddress.getLocalHost(), 8080);
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter out = new PrintWriter(server.getOutputStream());
        BufferedReader wt = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            out.println("{\"fromWho\":{\"ip\":\"10.13.26.120\",\"port\":8081,\"nickName\":\"Default\",\"identify\":\"Strange\",\"groupName\":\"hzf\"},\"targets\":[{\"ip\":\"10.13.26.120\",\"port\":8080,\"nickName\":null,\"identify\":\"Strange\",\"groupName\":null}],\"processType\":\"StrangeAskMediator\",\"content\":{\"questionType\":\"Hello\"}}");
            out.flush();


            System.out.println("2");
            System.out.println(in.readLine());
            System.out.println("3");

            String str = "end";

            if (str.equals("end"))
            {
                break;
            }
        }
        server.close();
    }
}
