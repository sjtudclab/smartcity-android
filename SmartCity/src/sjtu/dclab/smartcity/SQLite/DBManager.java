package sjtu.dclab.smartcity.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import sjtu.dclab.smartcity.chat.MessageEntity;
import sjtu.dclab.smartcity.community.entity.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yang on 2015/8/16.
 */
public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void saveMsg(Message msg) {
        db.execSQL(
                "INSERT INTO " + DBHelper.TABLE_NAME + " VALUES(null,?,?,?,?,?,?,?,?)",
                new Object[]{msg.getFrom(), msg.getTo(), msg.getType(), null, null, msg.getContent(), msg.getUserId(), msg.getName()}
        );
    }

    public List<MessageEntity> getMsg(long me, long peer) {
        ArrayList<MessageEntity> msgs = new ArrayList<MessageEntity>();
        Cursor cursor = getMsgCursor(me, peer);
        while (cursor.moveToNext()) {
            MessageEntity msg = new MessageEntity();
            msg.setContent(cursor.getString(cursor.getColumnIndex("content")));
            msg.setName(cursor.getString(cursor.getColumnIndex("name")));
            msgs.add(msg);
        }
        cursor.close();
        return msgs;
    }

    public Cursor getMsgCursor(long me, long peer) {
        String sql = "SELECT * FROM " + DBHelper.TABLE_NAME +
                " WHERE (fromId=? AND toId=?)" +
                " OR (toId=? AND fromId=?)" +
                " ORDER BY _id";
        Cursor cursor = db.rawQuery(sql, new String[]{me + "", peer + "", me + "", peer + ""});
        return cursor;
    }

    public void closeDB() {
        db.close();
    }
}
