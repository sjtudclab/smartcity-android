package sjtu.dclab.smartcity.chat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import sjtu.dclab.smartcity.R;

import java.io.Serializable;
import java.util.List;

/**
 * 2015年5月29日 下午7:18:23
 *
 * @author changyi yuan
 */
public class MessageAdapter extends ArrayAdapter<MessageEntity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3911252634913757203L;

	private Context context;

	private int resource;
	
	private List<MessageEntity> msgEntities;

	public MessageAdapter(Context context, int resource,
			int textViewResourceId, List<MessageEntity> msgEntities) {
		super(context, resource, textViewResourceId, msgEntities);
		this.context = context;
		this.resource = resource;
		this.msgEntities = msgEntities;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MessageEntity message = getItem(position);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(resource, null);

		TextView nameTextView = (TextView) view
				.findViewById(R.id.messagedetail_row_name);
		nameTextView.setText(message.getName());
		TextView contentTextView = (TextView) view
				.findViewById(R.id.messagedetail_row_text);
		contentTextView.setText(message.getContent());

		return view;
	}
	
	public List<MessageEntity> getMsgEntities(){
		return msgEntities;
	}

	public void setMsgEntities(List<MessageEntity> msgEntities){
		this.msgEntities = msgEntities;
	}
}
