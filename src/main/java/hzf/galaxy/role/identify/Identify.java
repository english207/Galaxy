package hzf.galaxy.role.identify;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public enum Identify
{
    Mediator("Mediator"),
    Leader("Leader"),
    Worker("Worker"),
    Strange("Strange");

    private String desc;
    Identify(String desc)
    {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
