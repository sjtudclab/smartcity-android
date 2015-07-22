package sjtu.dclab.smartcity.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.entity.Contact;
import sjtu.dclab.smartcity.tools.GsonTool;
import sjtu.dclab.smartcity.webservice.BasicWebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yang on 2015/7/22.
 */
public class ContactsFragment extends Fragment {
    final private String TAG = "fragment";

    private List<Contact> contacts;

    private ListView contactList;
    private ContactAdapter adapter;
    private ArrayList<HashMap<String,Object>> items = new ArrayList<HashMap<String, Object>>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"Fragment created");
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        //for network
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        BasicWebService service = new BasicWebService();
        //TODO friendID需要作为参数的！！！
        String url = getResources().getString(R.string.URLroot) + "friends/14/relations";
        String resp = service.sendGetRequest(url, null);

        contacts = GsonTool.getFriendList(resp);
        for (Contact friend:contacts){
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("icon",getResources().getDrawable(R.drawable.ic_launcher));
            map.put("name",friend.getName());
            map.put("id",friend.getContactId());
            items.add(map);
        }

        adapter = new ContactAdapter(
                getActivity().getApplicationContext(),
                items,
                R.layout.item_contact,
                new String[]{
                        "icon",
                        "name",
                        "id"},
                new int[]{
                        R.id.contact_icon,
                        R.id.contact_name,
                        R.id.contact_id
                });
        contactList = (ListView) getFragmentManager().findFragmentById(R.id.fragment_contact).getView().findViewById(R.id.lv_contacts);
        contactList.setAdapter(adapter);
        //contactList.setOnItemClickListener(new ItemOnClickListener());
    }

    class ContactAdapter extends SimpleAdapter {
        private int[] mTo;
        private String[] mFrom;
        private ViewBinder mViewBinder;
        protected List<? extends Map<String, ?>> mData;
        private int mResource;
        private LayoutInflater mInflater;

        public ContactAdapter(Context context,List<? extends Map<String, ?>> data, int resource, String[] from,int[] to) {
            super(context, data, resource, from, to);
            mData = data;
            mResource = resource;
            mFrom = from;
            mTo = to;
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return createViewFromResource(position, convertView, parent, mResource);
        }

        private View createViewFromResource(int position, View convertView,
                                            ViewGroup parent, int resource) {
            View v;
            if (convertView == null) {
                v = mInflater.inflate(resource, parent, false);

                final int[] to = mTo;
                final int count = to.length;
                final View[] holder = new View[count];

                for (int i = 0; i < count; i++) {
                    holder[i] = v.findViewById(to[i]);
                }
                v.setTag(holder);
            } else {
                v = convertView;
            }
            bindView(position, v);
            return v;
        }

        private void bindView(int position, View view) {
            final Map dataSet = mData.get(position);
            if (dataSet == null) {
                return;
            }

            final ViewBinder binder = mViewBinder;
            final View[] holder = (View[]) view.getTag();
            final String[] from = mFrom;
            final int[] to = mTo;
            final int count = to.length;

            for (int i = 0; i < count; i++) {
                final View v = holder[i];
                if (v != null) {
                    final Object data = dataSet.get(from[i]);
                    String text = data == null ? "" : data.toString();
                    if (text == null) {
                        text = "";
                    }

                    boolean bound = false;
                    if (binder != null) {
                        bound = binder.setViewValue(v, data, text);
                    }

                    if (!bound) {
                        //自定义适配器，关键在这里，根据传过来的控件类型以及值的数据类型，执行相应的方法
                        //可以根据自己需要自行添加if语句。另CheckBox等继承自TextView的控件也会被识别成TextView， 这就需要判断值的类型了
                        if (v instanceof TextView) {
                            //如果是TextView控件
                            setViewText((TextView) v, text);
                            //调用SimpleAdapter自带的方法，设置文本
                        } else if (v instanceof ImageView) {//如果是ImageView控件
                            setViewImage((ImageView) v, (Drawable) data);
                            //调用下面自己写的方法，设置图片
                        } else {
                            throw new IllegalStateException(v.getClass().getName() + " is not a " +
                                    " view that can be bounds by this SimpleAdapter");
                        }
                    }
                }
            }
        }

        public void setViewImage(ImageView v, Drawable value) {
            v.setImageDrawable(value);
        }

    }
}