package hzf.galaxy.role.conpoment;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.Map;

/**
 * Created by huangzhenfeng on 2016/5/4.
 *
 */
public abstract class IMBase
{
    private ObjectMapper jackSon = new ObjectMapper();

    public Map<String, Object> getContent(String content)
    {
        Map<String, Object> map = null;
        try
        {
            map = jackSon.readValue(content, Map.class);
        }
        catch (Exception e) { e.printStackTrace(); }
        return map;
    }

    public abstract String welCome(Map<String, Object> other);
    public abstract String ltns(Map<String, Object> other);

}
