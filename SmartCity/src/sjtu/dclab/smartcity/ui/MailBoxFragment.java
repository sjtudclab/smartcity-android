package sjtu.dclab.smartcity.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sjtu.dclab.smartcity.R;

/**MailBoxFragment extends Fragment
 * MailBoxFragment
 *
 * @author Jian Yang
 * @date 2015/7/9
 */
public class MailBoxFragment extends Fragment {
    static final String TAG = "MailBoxFragment";

    View view = null;
    TextView tvDone, tvDiscuss, tvWait, tvTransfer, tvFriends;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mailbox, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvWait = (TextView) view.findViewById(R.id.tv_mail_wait);
        tvDiscuss = (TextView) view.findViewById(R.id.tv_mail_discuss);
        tvDone = (TextView) view.findViewById(R.id.tv_mail_done);
        tvTransfer = (TextView) view.findViewById(R.id.tv_mail_transfer);
        tvFriends = (TextView) view.findViewById(R.id.tv_friends);
    }

    /**Button单击事件处理函数
     */
    public void onClickProcess(View v){
        if (v.getId() == R.id.tv_mail_wait){

        }else if (v.getId() == R.id.tv_mail_discuss){

        }else if (v.getId() == R.id.tv_mail_done){

        }else if (v.getId() == R.id.tv_mail_transfer){

        }else if (v.getId() == R.id.tv_friends){

        }
    }

}