package hzf.galaxy.role.question;

/**
 * Created by huangzhenfeng on 2016/5/3.
 *
 */
public enum  QuestionType
{
    Hello("Hello"),
    LTNS("LONG TIME NO SEE"),
    Hello1("Hello"),
    Hello2("Hello");

    private String type;

    QuestionType(String type)
    {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
