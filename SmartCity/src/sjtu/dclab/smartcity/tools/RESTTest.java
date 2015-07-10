package sjtu.dclab.smartcity.tools;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import sjtu.dclab.smartcity.model.Mail;

/**RESTTest
 * RESTTest
 *
 * @author Jian Yang
 * @date 2015/7/9
 */
public class RESTTest {
    static final String TAG = "RESTTest";

    /**
     * @param context
     */
    public static void RequestForJson(final Context context, String urlBase, String userID, String restCommond) {
//        RequestParams params = new RequestParams();
//        params.put("imei", "862950025623748");
//        Toast.makeText(context, "Sending: ", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();//创建客户端对象
        String url = urlBase + "/" + userID + "/" + restCommond;
//        Log.i(TAG, "url: " + url);
        client.get(url, new JsonHttpResponseHandler() {
            @Override//返回JSONArray对象 | JSONObject对象
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    Gson gson = new Gson();
                    List<Mail> mailsList = gson.fromJson(String.valueOf(response), new TypeToken< List<Mail> >(){}.getType());
                    if (mailsList != null){
                        Log.i(TAG, mailsList.toString());
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
