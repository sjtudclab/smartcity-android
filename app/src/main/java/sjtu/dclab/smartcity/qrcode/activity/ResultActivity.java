package sjtu.dclab.smartcity.qrcode.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.qrcode.decode.DecodeThread;
import sjtu.dclab.smartcity.tools.SharedDataUtil;
import sjtu.dclab.smartcity.webservice.BasicWebService;

public class ResultActivity extends Activity {
	private static final String TAG = ResultActivity.class.getSimpleName();
	
	private String URLROOT;
	private String URL_BASE_REQUEST_FOR_FRIEND;

	private ImageView mResultImage;
	private TextView mResultText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		URLROOT = getResources().getString(R.string.URLRoot);
		URL_BASE_REQUEST_FOR_FRIEND = URLROOT + "friends/";

		Bundle extras = getIntent().getExtras();

		mResultImage = (ImageView) findViewById(R.id.result_image);
		mResultText = (TextView) findViewById(R.id.result_text);

		if (null != extras) {
			int width = extras.getInt("width");
			int height = extras.getInt("height");

			LayoutParams lps = new LayoutParams(width, height);
			lps.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
			lps.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
			lps.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
			
			mResultImage.setLayoutParams(lps);

			String result = extras.getString("result");
			mResultText.setText(result);
			SharedDataUtil.writeQRString(getApplication(), result);

			Bitmap barcode = null;
			byte[] compressedBitmap = extras.getByteArray(DecodeThread.BARCODE_BITMAP);
			if (compressedBitmap != null) {
				barcode = BitmapFactory.decodeByteArray(compressedBitmap, 0, compressedBitmap.length, null);
				// Mutable copy:
				barcode = barcode.copy(Bitmap.Config.RGB_565, true);
			}
			mResultImage.setImageBitmap(barcode);
			
			//弹出添加好友提示
			AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
			LayoutInflater factory = LayoutInflater.from(ResultActivity.this);
			final View textEntryView = factory.inflate(R.layout.dialog_friend_req, null);
			builder.setTitle("请求" + result.split("_")[1] + "为联系人");
			builder.setView(textEntryView);
			builder.setPositiveButton("发送", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					EditText et_msg = (EditText) textEntryView.findViewById(R.id.et_msg_dialog_fiendreq);
					//发送请求到服务器
					String friendId = SharedDataUtil.getQRString(getApplication()).split("_")[0];//TODO 测试阶段
					String url = URL_BASE_REQUEST_FOR_FRIEND + Me.id + "/applications/" + friendId;
//					Log.i(TAG, "url: " + url);
					JSONObject jsonObject = new JSONObject();
					try {
						jsonObject.put("message", et_msg.getText().toString());
						String res = new BasicWebService().sendPostRequestWithRawJson(url, jsonObject);
						if (res.equals("success")){
							Toast.makeText(getApplicationContext(),"请求已成功发送",Toast.LENGTH_SHORT).show();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(),"请求失败",Toast.LENGTH_SHORT).show();
					}
				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {}
			});
			builder.create().show();
		}//if
		
	}
}
