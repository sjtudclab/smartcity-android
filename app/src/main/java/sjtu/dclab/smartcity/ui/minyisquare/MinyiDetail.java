package sjtu.dclab.smartcity.ui.minyisquare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.json.JSONObject;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.community.config.Me;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.transfer.ReplyTransfer;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinyiDetail extends Activity {
    private TextView tv_user;
    private TextView tv_title;
    private TextView tv_content;
    private int post_id;

    private ListView lv_replies;


    private EditText ed_content;
    private Button btn_send;

    private String rootUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minyi_detail);

        Intent intent = getIntent();

        tv_user = (TextView) findViewById(R.id.tv_minyi_itemUser);
        tv_user.setText("这里是用户民");
        tv_title = (TextView) findViewById(R.id.itemTit);
        tv_title.setText(intent.getExtras().get("title").toString());
        tv_content = (TextView) findViewById(R.id.itemCon);
        tv_content.setText(intent.getExtras().get("content").toString());
        post_id = Integer.parseInt(intent.getExtras().get("id").toString());

        ed_content = (EditText) findViewById(R.id.et_reply_content);
        btn_send = (Button) findViewById(R.id.btn_reply_send);
        btn_send.setOnClickListener(new ReplyBtnListener());

        rootUrl = getResources().getString(R.string.URLRoot);

        init();
    }

    protected void init() {
        lv_replies = (ListView) findViewById(R.id.lv_replies);
        String url = rootUrl + "bbspost/" + post_id + "/replies";
        String resp = new BasicWebService().sendGetRequest(url, null);
        List<ReplyTransfer> replyTransferList = GsonTool.getObjectList(resp, ReplyTransfer[].class);
        List<Map<String, Object>> replyList = new ArrayList<Map<String, Object>>();
        if (!replyTransferList.isEmpty()) {
            for (ReplyTransfer r : replyTransferList) {
                HashMap<String, Object> item = new HashMap<String, Object>();
                item.put("title", r.getBbs_replier_id());
                item.put("content", r.getBbs_reply_content());
                item.put("bbs_post_id", r.getBbs_post_id());
                item.put("bbs_another_reply_id", r.getBbs_another_reply_id());
                item.put("bbs_reply_id", r.getBbs_reply_id());
                replyList.add(item);
            }

            lv_replies.setAdapter(new ReplyItemAdapter(getApplicationContext(), replyList));
        }

    }

    class ReplyBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String content = ed_content.getText().toString();
            Context context = getApplicationContext();
            if (!content.isEmpty()) {
                try {
                    String url = rootUrl + "bbspost/reply/" + post_id;
                    JSONObject jb = new JSONObject();
                    jb.put("bbs_post_id", post_id);
                    jb.put("bbs_replier_id", Me.id);
                    jb.put("bbs_reply_content", content);
                    jb.put("bbs_another_reply_id", 1);
                    jb.put("bbs_reply_time", System.currentTimeMillis());
                    String res = new BasicWebService().sendPostRequestWithRawJson(url, jb);
                    if (res.equals("success")) {
                        Toast.makeText(context, "发送成功", Toast.LENGTH_SHORT).show();
                        ed_content.setText("");
                        init();
                    } else {
                        Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "发送失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "内容不能为空", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class ReplyItemAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<? extends Map<String, ?>> data;

        public ReplyItemAdapter(Context context, List<? extends Map<String, ?>> data) {
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return this.data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            String secondaryReplies = "";
            if (view == null) {
                view = mInflater.inflate(R.layout.bbs_reply_item, null);

                holder = new ViewHolder();
                holder.title = (TextView) view.findViewById(R.id.tv_bbs_reply_username);
                holder.content = (TextView) view.findViewById(R.id.tv_bbs_reply_content);
                holder.secondaryReply = (TextView) view.findViewById(R.id.tv_secondary_reply);
                holder.reply = (ImageButton) view.findViewById(R.id.ibtn_comment_reply);
                String url = rootUrl + "bbspost/" + post_id + "/" + this.data.get(i).get("bbs_reply_id") + "/replies";
                String resp = new BasicWebService().sendGetRequest(url, null);
                List<ReplyTransfer> rtList = GsonTool.getObjectList(resp, ReplyTransfer[].class);

                for (ReplyTransfer rt : rtList) {
                    String reply = rt.getBbs_replier_id() + ":" + rt.getBbs_reply_content()+"\n";
                    secondaryReplies += reply;
                }

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.title.setText("用户名");
            holder.content.setText((CharSequence) this.data.get(i).get("content"));
            holder.reply.setOnClickListener(new ReplyListener(Integer.parseInt(this.data.get(i).get("bbs_reply_id").toString())));
            holder.secondaryReply.setText(secondaryReplies);

            return view;
        }
    }

    private final class ViewHolder {
        private TextView title;
        private TextView content;
        private TextView secondaryReply;
        private ImageButton reply;
        private Button like;
    }

    // 对一级评论回复
    private class ReplyListener implements View.OnClickListener {
        private int reply_id;

        public ReplyListener(int reply_id) {
            this.reply_id = reply_id;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), SecondaryReplyAty.class);
            intent.putExtra("post_id", post_id);
            intent.putExtra("reply_id", this.reply_id);
            startActivity(intent);
        }
    }
}
