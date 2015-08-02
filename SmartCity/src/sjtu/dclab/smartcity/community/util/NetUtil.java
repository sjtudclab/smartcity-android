package sjtu.dclab.smartcity.community.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 2015年5月6日 下午3:31:09
 *
 * @author changyi yuan
 */
public class NetUtil {

	public static String sendGet(String addr, String content) throws Exception {
		if(content != null && content.trim().length() != 0)
			addr += "?" + content;
		URL url = new URL(addr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null)
			sb.append(line);

//		pw.close();
		br.close();
		
		conn.disconnect();

		return sb.toString();
	}

	public static String sendPost(String addr, String content, String contentType) throws Exception {
		URL url = new URL(addr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		if(contentType == null || contentType.trim().length() == 0)
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		else
			conn.setRequestProperty("Content-Type", contentType);
		conn.setRequestProperty("Content-Length", content.length()+"");

		conn.setDoInput(true);
		conn.setDoOutput(true);
		
		conn.connect();
		
		PrintWriter pw = new PrintWriter(conn.getOutputStream());

		pw.print(content);
		pw.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null)
			sb.append(line);

		pw.close();
		br.close();
		
		conn.disconnect();

		return sb.toString();
	}

	public static String sendPost(String addr, Map<String, String> params, String contentType) throws Exception {
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),"utf-8"))
					.append("&");
		}
		if(sb.length() > 0)
			sb.deleteCharAt(sb.length() - 1);
		
		return sendPost(addr, sb.toString(), contentType);
	}
}
