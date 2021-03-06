package sjtu.dclab.smartcity;

import android.app.Application;
import sjtu.dclab.smartcity.community.talk.MyTalk;

/**
 * Created by Yang on 2015/7/23.
 */
public class GlobalApp extends Application {
    private String userId;
    private String username;
    private String status;
    private MyTalk talk;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
