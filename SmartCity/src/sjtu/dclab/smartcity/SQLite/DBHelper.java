package sjtu.dclab.smartcity.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yang on 2015/8/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    protected static final String DATABASE_NAME = "chat_record.db";
    protected static final String TABLE_NAME = "messages_record";
    protected static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // fields refers to Message.java
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fromId INTEGER, " +
                "toId INTEGER, " +
                "type INTEGER, "+
                "contentType INTEGER, " +
                "id INTEGER, " +
                "content TEXT, " +
                "userId INTEGER, " +
                "name VARCHAR)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        sqLiteDatabase.execSQL("ALTER TABLE messages_record ADD COLUMN other STRING");
    }
}
