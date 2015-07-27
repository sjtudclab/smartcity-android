package sjtu.dclab.smartcity.webservice;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Yang on 2015/7/9.
 */

public class BasicWebService {
    protected HttpClient httpclient;

    private final int REQUEST_TIMEOUT = 10 * 1000;// 设置请求超时10秒钟

    private final int SO_TIMEOUT = 10 * 1000; // 设置等待数据超时时间10秒钟

    private final String ERRORMSG = "error";

    public BasicWebService() {
        BasicHttpParams httpParams = new BasicHttpParams();
        httpParams.setParameter("charset", HTTP.UTF_8);
        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
        httpclient = new DefaultHttpClient(httpParams);
    }

    public String sendPostRequest(String url, Map<String, String> args) {
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Accept", "application/json");
            if (args != null) {
                List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
                for (Map.Entry<String, String> entry : args.entrySet()) {
                    postData.add(new BasicNameValuePair(entry.getKey(), entry
                            .getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                        postData, HTTP.UTF_8);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity1 = response.getEntity();
            InputStream instream = entity1.getContent();
            String jaxrsmessage = "";
            jaxrsmessage = Reader.read(instream);
            Log.i("jaxrsmessage", jaxrsmessage);
            httpPost.abort();
            return jaxrsmessage;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
//            return Message.NETWORK_FAIL;
            return ERRORMSG;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return Message.NETWORK_FAIL;
        return ERRORMSG;
    }

    public String sendGetRequest(String url, Map<String, String> args) {
        try {
            StringBuffer sBuffer = new StringBuffer();
            if (args != null) {
                sBuffer.append("?");
                for (String key : args.keySet()) {
                    sBuffer.append(key + "=" + args.get(key) + "&");
                }
            }

            HttpGet httpGet = new HttpGet(url + sBuffer.toString());
            Log.e("url", url + sBuffer.toString());
            httpGet.addHeader("Accept", "application/json");
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity1 = response.getEntity();
            InputStream instream = entity1.getContent();
            String jaxrsmessage = "";
            jaxrsmessage = Reader.read(instream);
            Log.i("jaxrsmessage", jaxrsmessage);
            httpGet.abort();
            return jaxrsmessage;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
//            return Message.NETWORK_FAIL;
            return ERRORMSG;
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return Message.NETWORK_FAIL;
        return ERRORMSG;
    }
}