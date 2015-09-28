package sjtu.dclab.smartcity.ui.chat;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.tools.QRCodeTool;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QRCodeTestAty extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_qrcodetest);
		
		TextView tv1 = (TextView) findViewById(R.id.txView);
		Button btn1 = (Button) findViewById(R.id.btn1);
		btn1.setVisibility(View.VISIBLE);
		btn1.setText("Click");
		ImageView iv1 = (ImageView) findViewById(R.id.iv1);
		iv1.setVisibility(View.VISIBLE);
		
		tv1.setText("3_zhangzhizhong");
		Bitmap bm = QRCodeTool.createQRBitmap("3_zhangzhizhong");
//		tv1.setText(Me.id + "_" + Me.username);
//		Bitmap bm = QRCodeTool.createQRBitmap(Me.id + "_" + Me.username);
		BitmapDrawable bd= new BitmapDrawable(getResources(), bm);
		iv1.setImageDrawable(bd);
	}

	/**Button单击事件处理函数
	 * @param view
	 */
	public void onClickProcess(View v){
		
	}
}
