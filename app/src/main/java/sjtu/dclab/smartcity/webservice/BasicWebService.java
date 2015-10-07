package sjtu.dclab.smartcity.webservice;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Yang on 2015/7/9.
 */

public class BasicWebService {
    private final String TAG = "BasicWebService";

    protected HttpClient httpClient;

    private final int REQUEST_TIMEOUT = 10 * 1000;// 设置请求超时10秒钟

    private final int SO_TIMEOUT = 10 * 1000; // 设置等待数据超时时间10秒钟

    private final String ERRORMSG = "error";
    private final String SUCCESS = "success";

    public BasicWebService() {
        BasicHttpParams httpParams = new BasicHttpParams();
        httpParams.setParameter("charset", HTTP.UTF_8);
        HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
        httpClient = new DefaultHttpClient(httpParams);
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
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            InputStream instream = entity.getContent();
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

    public String sendPostRequestWithRawJson(String url, JSONObject jsonObject) {
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new ByteArrayEntity(jsonObject.toString().getBytes()));
            HttpResponse response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine != null && statusLine.getStatusCode() / 100 == 2) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    String jaxrsmessage = "";
                    jaxrsmessage = Reader.read(instream);
                    Log.i("jaxrsmessage", jaxrsmessage);
                    httpPost.abort();
                    return jaxrsmessage;
                } else {
                    Log.i(TAG, "null");
                    return ERRORMSG;
                }
            } else {
                Log.i(TAG, ERRORMSG);
                return ERRORMSG;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERRORMSG;
        }
    }

    public String sendPostRequestWithJsonString(String url, String jsonStr) {
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new ByteArrayEntity(jsonStr.getBytes()));
            HttpResponse response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine != null && statusLine.getStatusCode() / 100 == 2) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    String jaxrsmessage = "";
                    jaxrsmessage = Reader.read(instream);
                    Log.i("jaxrsmessage", jaxrsmessage);
                    httpPost.abort();
                    return jaxrsmessage;
                } else {
                    Log.i(TAG, "null");
                    return ERRORMSG;
                }
            } else {
                Log.i(TAG, ERRORMSG);
                return ERRORMSG;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERRORMSG;
        }
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
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream instream = entity.getContent();
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


    public String sendPostRequestWithMultipartEntity(String url, MultipartEntity entity) {
        try {
            HttpPost httpPost = new HttpPost(url);
            HttpResponse httpResponse;
            httpPost.setEntity(entity);
            httpResponse = httpClient.execute(httpPost);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Log.i(TAG, "StatusCode=" + statusCode);
            if (statusCode / 100 == 2) {
                return SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERRORMSG;
    }


    public String sendPutRequest(String url, Map<String, String> args) {
        try {
            HttpPut httpPut = new HttpPut(url);
            if (args != null) {
                List<BasicNameValuePair> putData = new ArrayList<BasicNameValuePair>();
                for (Map.Entry<String, String> entry : args.entrySet()) {
                    putData.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(putData, HTTP.UTF_8);
                httpPut.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            String result = EntityUtils.toString(entity, HTTP.UTF_8);
            return result;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return ERRORMSG;
        } catch (IOException io) {
            io.printStackTrace();
        }
        return ERRORMSG;
    }
}