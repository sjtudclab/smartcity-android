package sjtu.dclab.smartcity.tools;

import android.content.Context;
import android.util.Log;

<<<<<<< HEAD
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
=======
>>>>>>> b24116a7aa04cbf811e8f374582f4101f0c4df64
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
<<<<<<< HEAD
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import sjtu.dclab.smartcity.model.Mail;

=======
import org.json.JSONObject;

>>>>>>> b24116a7aa04cbf811e8f374582f4101f0c4df64
/**RESTTest
 * RESTTest
 *
 * @author Jian Yang
 * @date 2015/7/9
 */
public class RESTTest {
    static final String TAG = "RESTTest";

<<<<<<< HEAD
    /**
     * @param context
     */
    public static void RequestForJson(final Context context, String urlBase, String userID, String restCommond) {
//        RequestParams params = new RequestParams();
//        params.put("imei", "862950025623748");
//        Toast.makeText(context, "Sending: ", Toast.LENGTH_SHORT).show();
        AsyncHttpClient client = new AsyncHttpClient();//åˆ›å»ºå®¢æˆ·ç«¯å¯¹è±¡
        String url = urlBase + "/" + userID + "/" + restCommond;
//        Log.i(TAG, "url: " + url);
        client.get(url, new JsonHttpResponseHandler() {
            @Override//è¿”å›žJSONArrayå¯¹è±¡ | JSONObjectå¯¹è±¡
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
=======
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
        AsyncHttpClient client = new AsyncHttpClient();//´´½¨¿Í»§¶Ë¶ÔÏó
        client.post(urlBase + "/" + userID + "/" + restCommond, new JsonHttpResponseHandler() {
            @Override//·µ»ØJSONArray¶ÔÏó | JSONObject¶ÔÏó
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    Log.i(TAG, response.toString());
>>>>>>> b24116a7aa04cbf811e8f374582f4101f0c4df64
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
