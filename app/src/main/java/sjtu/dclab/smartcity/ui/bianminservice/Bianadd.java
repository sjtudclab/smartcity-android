package sjtu.dclab.smartcity.ui.bianminservice;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.tools.IOTools;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.io.File;
import java.io.InputStream;

/**
 * Created by HuangZhenyu on 15/7/27.
 */
public class Bianadd extends Activity {
    private static final String TAG = "Bianadd";

    private String URLRoot = null;

    private EditText etType, etTitle, etDescription, etPlace, etName, etPhone;
    private Button btnPhoto, btnOk, btnCancle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_add_yiwu);

        URLRoot = getResources().getString(R.string.URLRoot);

        etType = (EditText) findViewById(R.id.et_publish_type);
        etTitle = (EditText) findViewById(R.id.et_publish_title);
        etDescription = (EditText) findViewById(R.id.et_publish_desc);
        etPlace = (EditText) findViewById(R.id.et_publish_place);
        etName = (EditText) findViewById(R.id.et_publish_name);
        etPhone = (EditText) findViewById(R.id.et_publish_phone);
        btnPhoto = (Button) findViewById(R.id.btn_publish_photo);
        btnOk = (Button) findViewById(R.id.btn_publish_ok);
        btnCancle = (Button) findViewById(R.id.btn_publish_cancle);

        btnOk.setOnClickListener(new PublishListener());
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class PublishListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                String type = "转让";
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                String category = etType.getText().toString();
                String place = etPlace.getText().toString();
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();

                MultipartEntity args = new MultipartEntity();
                args.addPart("dealType", new StringBody(type));
                args.addPart("goodsTitle", new StringBody(title));
                args.addPart("goodsDescribe", new StringBody(description));
                args.addPart("type1", new StringBody(category));
                args.addPart("address", new StringBody(place));
                args.addPart("contactTel", new StringBody(phone));
                args.addPart("contactName", new StringBody(name));

                InputStream inputStream = getResources().getAssets().open("defaultpic.png");
                File file = new File(getFilesDir() + "/defaultpic.png");
                if(!file.exists()) file.createNewFile();
                IOTools.inputStreamToFile(inputStream, file);
                args.addPart("file", new FileBody(file));//TODO：照片上传

                String resp = new BasicWebService().sendPostRequestWithMultipartEntity(
                        URLRoot + "publish/create", args, false);
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
}
