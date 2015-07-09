package sjtu.dclab.smartcity.tools;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

/**RESTTest
 * RESTTest
 *
 * @author Jian Yang
 * @date 2015/7/9
 */
public class RESTTest {
    static final String TAG = "RESTTest";

    public static final String URL_REST_MAILBOX_BASE = "202.120.40.111:8080/community-server/rest/mailboxs";
    public static final String WAIT = "waiting";
    public static final String DONE = "done";
    public static final String DISCUSS = "discussed";
    public static final String TRANSFER = "transferred";

    /**
     * @param context
     */
    public static void RequestForJson(Context context, String urlBase, String userID, String restCommond) {
//        RequestParams params = new RequestParams();
//        params.put("imei", "862950025623748");
//        Toast.makeText(context, "Sending: ", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();//创建客户端对象
        client.post(urlBase + "/" + userID + "/" + restCommond, new JsonHttpResponseHandler() {
            @Override//返回JSONArray对象 | JSONObject对象
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    Log.i(TAG, response.toString());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
