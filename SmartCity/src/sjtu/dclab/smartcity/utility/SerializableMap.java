package sjtu.dclab.smartcity.utility;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by hp on 2015/7/22.
 */
public class SerializableMap implements Serializable {
    private Map<String,Object> map;
    public Map<String,Object> getMap()
    {
        return map;
    }
    public void setMap(Map<String,Object> map)
    {
        this.map=map;
    }
}
