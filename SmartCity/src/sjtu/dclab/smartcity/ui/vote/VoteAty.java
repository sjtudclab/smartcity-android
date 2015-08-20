package sjtu.dclab.smartcity.ui.vote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.transfer.TopicTransfer;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by theGODofws on 2015/7/30.
 * Rewritten by Kaffa
 */
public class VoteAty extends Activity {

    private ImageButton ibtnRtn;
    private ImageButton ibtnAdd;
    private ListView lvVote;
    private RadioGroup rgVote;
    private RadioButton rbAgree;
    private RadioButton rbDisag;
    private List<TopicTransfer> ttList = new ArrayList<TopicTransfer>();
    private ArrayList<HashMap<String, Object>> voteList = new ArrayList<HashMap<String, Object>>();

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

        init();
    }

    public void init() {
        String url = getResources().getString(R.string.URLRoot) + getResources().getString(R.string.URLGetVote);
        String resp = webService.sendGetRequest(url, null);
        ttList = GsonTool.getObjectList(resp, TopicTransfer[].class);
        for (TopicTransfer vote : ttList) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("title", vote.getTitle());
            item.put("content", vote.getContent());
            voteList.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getApplicationContext(), voteList, R.layout.vote_item,
                new String[]{"title", "content"},
                new int[]{R.id.tv_vote_title, R.id.tv_vote_content}
        );

        lvVote.setAdapter(adapter);
    }

    private class AddBtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), NewVoteAty.class));
        }
    }

    // TODO 投票结果的监听
    private class VoteListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int rbtnId = radioGroup.getCheckedRadioButtonId();
            Context context = getApplicationContext();
            switch (rbtnId){
                case R.id.rb_vote_agree:
                    Toast.makeText(context,"您投了支持，谢谢合作！",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rb_vote_disagree:
                    Toast.makeText(context,"您投了反对，谢谢合作！",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
