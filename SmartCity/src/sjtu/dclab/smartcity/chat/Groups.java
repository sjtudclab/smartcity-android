package sjtu.dclab.smartcity.chat;

import sjtu.dclab.smartcity.community.entity.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yang on 2015/8/7.
 * 仿照袁长意的Friends写的
 */
public class Groups {
    private static Map<Long,Group> gs = new HashMap<Long,Group>();

    public static Group getGroup(long id){
        return gs.get(id);
    }

    public static void addFriend(Group group){
        gs.put(group.getId(), group);
    }

    public static void addGroups(List<Group> gs){
        if(gs != null){
            for(Group g : gs)
                addFriend(g);
        }
    }

    public static List<Group> getGroups(){
        List<Group> temp = new ArrayList<Group>();
        for(long id : gs.keySet())
            temp.add(gs.get(id));
        return temp;
    }
}
