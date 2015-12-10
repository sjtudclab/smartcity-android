package sjtu.dclab.smartcity.ui.vote;

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
import sjtu.dclab.smartcity.transfer.TopicTransfer;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.*;

/**
 * Created by theGODofws on 2015/7/30.
 * Rewritten by Kaffa
 */
public class VoteAty extends Activity {

    private ImageButton ibtnRtn;
    private ImageButton ibtnAdd;
    private ListView lvVote;

    private ArrayList<HashMap<String, Object>> voteList;

    private BasicWebService webService = new BasicWebService();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_vote);

        ibtnRtn = (ImageButton) findViewById(R.id.vote_rtn);
        ibtnRtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ibtnAdd = (ImageButton) findViewById(R.id.ibtn_vote_add);
        ibtnAdd.setOnClickListener(new AddBtnListener());

        lvVote = (ListView) findViewById(R.id.lv_vote);
    }


    @Override
    protected void onStart() {
        super.onStart();
        updateData();
    }

    public void updateData() {
        String url = getResources().getString(R.string.URLRoot) + getResources().getString(R.string.URLGetVote);
        String resp = webService.sendGetRequest(url, null);
        List<TopicTransfer> ttList = null;
        ttList = GsonTool.getObjectList(resp, TopicTransfer[].class);
        voteList = new ArrayList<HashMap<String, Object>>();
        Collections.reverse(ttList);
        for (TopicTransfer vote : ttList) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("topic_id", vote.getTopic_id());
            item.put("title", vote.getTitle());
            item.put("content", vote.getContent());
            voteList.add(item);
        }

        VoteItemAdapter voteItemAdapter = new VoteItemAdapter(getApplicationContext(), voteList);
        lvVote.setAdapter(voteItemAdapter);
    }

    private class AddBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), NewVoteAty.class));
        }
    }

    private class VoteItemAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<? extends Map<String, ?>> data;

        public VoteItemAdapter(Context context, List<? extends Map<String, ?>> data) {
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public int getCount() {
            return this.data.size();
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
            if (view == null) {
                view = mInflater.inflate(R.layout.vote_item, null);

                holder = new ViewHolder();
                holder.title = (TextView) view.findViewById(R.id.tv_vote_title);
                holder.content = (TextView) view.findViewById(R.id.tv_vote_content);
                holder.rg = (RadioGroup) view.findViewById(R.id.rg_vote);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.title.setText((CharSequence) this.data.get(i).get("title"));
            holder.content.setText((CharSequence) this.data.get(i).get("content"));
            String id = this.data.get(i).get("topic_id") + "";
            holder.rg.setOnCheckedChangeListener(new VoteListener(id));
            return view;
        }
    }

    private final class ViewHolder {
        private TextView title;
        private TextView content;
        private RadioGroup rg;
    }

    //投票结果的监听
    private class VoteListener implements RadioGroup.OnCheckedChangeListener {
        private String topic_id;

        public VoteListener(String topic_id) {
            this.topic_id = topic_id;
        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int rbtnId = radioGroup.getCheckedRadioButtonId();
            Context context = getApplicationContext();
            JSONObject jsonObject = new JSONObject();
            String url = context.getResources().getString(R.string.URLRoot) + "topic/vote";
            switch (rbtnId) {
                case R.id.rb_vote_agree:
                    try {
                        jsonObject.put("option_id", "1");
                        jsonObject.put("user_id", Me.id + "");
                        jsonObject.put("topic_id", this.topic_id);
                        String res = new BasicWebService().sendPostRequestWithRawJson(url, jsonObject);
                        if (res.equals(""))
                            Toast.makeText(context, "您投了赞成，谢谢合作！", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.rb_vote_disagree:
                    try {
                        jsonObject.put("option_id", "1");
                        jsonObject.put("user_id", Me.id + "");
                        jsonObject.put("topic_id", this.topic_id);
                        String res = new BasicWebService().sendPostRequestWithRawJson(url, jsonObject);
                        if (res.equals(""))
                            Toast.makeText(context, "您投了反对，谢谢合作！", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
