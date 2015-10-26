package sjtu.dclab.smartcity.chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2015年5月29日 下午6:35:51
 *
 * @author changyi yuan
 */
public class DeprecatedMessages {
    //username : messages
    private static Map<String, DeprecatedMessageAdapter> msgs = new HashMap<String, DeprecatedMessageAdapter>();

    public static DeprecatedMessageAdapter loadMessages(String username) {
        return msgs.get(username);
    }

    public static void storeMessageAdapter(String username, DeprecatedMessageAdapter adapter) {
        msgs.put(username, adapter);
    }

    //flag是否通知adapter数据更改
    public static void storeMessageEntity(String username, MessageEntity msgEntity, boolean flag) {
        DeprecatedMessageAdapter adapter = msgs.get(username);
        List<MessageEntity> msgEntities = adapter.getMsgEntities();
        msgEntities.add(msgEntity);
        if (flag)
            adapter.notifyDataSetChanged();
    }
}
