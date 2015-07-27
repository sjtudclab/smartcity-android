package sjtu.dclab.smartcity;

import android.app.Application;

/**
 * Created by Yang on 2015/7/23.
 */
public class GlobalApp extends Application {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
