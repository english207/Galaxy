package hzf.galaxy.beans;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public class Test extends Worker
{
    public static void main(String[] args) {
        Test test  = new Test();
        test.setIp("111");
        System.out.println(test.getIp());
    }
}
