package sjtu.dclab.smartcity.ui.ann_committee;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.io.File;

/**
 * AnnPublishAty
 *
 * @author Kaffa
 * @date 2015/7/23
 */
public class AnnPublishAty extends Activity {

    private String TAG = "AnnPublishAty";

    private ImageButton btnRet;
    private EditText etTitle;
    private EditText etContent;
    private Button btnUpload;
    private Button btnPublish;

    private BasicWebService webService = new BasicWebService();
    private String url;

    private String filePath = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_ann_pubish);

        url = getResources().getString(R.string.URLRoot) + getResources().getString(R.string.URLPublishAnn);

        btnRet = (ImageButton) findViewById(R.id.ann_publish_rtn);
        etTitle = (EditText) findViewById(R.id.et_new_ann_title);
        etContent = (EditText) findViewById(R.id.et_new_ann_content);
        btnUpload = (Button) findViewById(R.id.btn_new_ann_upload);
        btnPublish = (Button) findViewById(R.id.btn_new_ann_publish);

        etTitle.setHint("标题");
        etContent.setHint("正文");

        btnRet.setOnClickListener(new RtnListener());
        btnUpload.setOnClickListener(new UploadListener());
        btnPublish.setOnClickListener(new PublishListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult:" + requestCode + ", Excepted:" + RESULT_OK);
        if (requestCode == 1) {
            Uri uri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(uri, projection, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            filePath = cursor.getString(column_index);
            Log.i(TAG, "filePath:" + filePath);
            btnUpload.setText(filePath);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class RtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    private class UploadListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(intent, 1);
            Log.d(TAG, "filepath: " + filePath);
        }
    }

    private class PublishListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            try {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                String from = Me.id + "";
                String infotype = "1";
                String redirect_url = "/committee/notice";
                MultipartEntity args = new MultipartEntity();
                args.addPart("title", new StringBody(title));
                args.addPart("content", new StringBody(content));
                args.addPart("from", new StringBody(from));
                args.addPart("infotype", new StringBody(infotype));
                if (!filePath.equals("")) {
                    File uploadFile = new File(filePath);
                    args.addPart("file", new FileBody(uploadFile));
                }
                args.addPart("redirect_url", new StringBody(redirect_url));
                String resp = webService.sendPostRequestWithMultipartEntity(url, args, false);
                if (resp == "success") {
                    Toast.makeText(getApplicationContext(), "发布成功！", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "发布失败！", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "状态码!=200");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "exception");
                Toast.makeText(getApplicationContext(), "发布失败！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}