package sjtu.dclab.smartcity;

import android.app.Application;
import cn.edu.sjtu.se.dclab.talk.MyTalk;

/**
 * Created by Yang on 2015/7/23.
 */
public class GlobalApp extends Application {
    private String username;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private MyTalk talk;

    public MyTalk getTalk() {
        return talk;
    }

    public void setTalk(MyTalk talk) {
        this.talk = talk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
