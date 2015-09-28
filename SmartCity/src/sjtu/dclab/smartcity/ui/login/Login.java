package sjtu.dclab.smartcity.ui.login;

import android.content.Context;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.transfer.UserTransfer;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yang on 2015/7/27.
 */
public class Login {


    static public UserTransfer login(Context c, String name, String passwd) {
        String URLLOGIN = c.getString(R.string.URLRoot) + c.getString(R.string.URLLogin);
//        String URLLOGIN = "http://202.120.40.111:8080/community-server/rest/users/login";

        BasicWebService webService = new BasicWebService();
        Map<String, String> kvs = new HashMap<String, String>();
        String res = "";
        UserTransfer ut = null;
        kvs.put("username", name);
        kvs.put("password", passwd);

        res = webService.sendPostRequest(URLLOGIN, kvs);
        if (res != "") {
            ut = GsonTool.getObject(res, UserTransfer.class);
        }
        return ut;
    }
}
