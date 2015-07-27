package sjtu.dclab.smartcity.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sjtu.dclab.smartcity.R;
import sjtu.dclab.smartcity.utility.SerializableMap;

/**
 * Created by hp on 2015/7/21.
 */
public class ContactsFragment extends Fragment {
   // private getDataListener mListener;

    private TextView questText;
    private TextView friendText;
    private TextView groupText;

    private ListView questList;
    private ListView groupList;
    private ListView firendList;

//    public ContactsFragment(){super();}
//    public ContactsFragment(Bundle b){
//        super();
//    }
/*
    private Bundle bundle;

    public interface getDataListener{
        public SerializableMap getQuestMap();
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            mListener = (getDataListener) activity;
        } catch (ClassCastException e){}
    }

*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        Bundle bundle = getArguments();
        Bundle b = getActivity().getIntent().getExtras();
    //    System.out.println("System output bundle:" + bundle);
    //    System.out.println("System output bundle:" + bundle.getSerializable("questMap"));
        SerializableMap questMap = (SerializableMap) bundle.getSerializable("questMap");
     //   System.out.println("System output bundle:" + getFragmentManager());
     //   System.out.println("System output bundle:" + getFragmentManager().findFragmentByTag("1234567").getArguments());
        SerializableMap friendMap = new SerializableMap();
        SerializableMap groupMap = new SerializableMap();
     //   SerializableMap questMap = (SerializableMap) getFragmentManager().findFragmentByTag("1234567").getArguments().getSerializable("questMap"); //new HashMap<String,Object>();
      //  SerializableMap friendMap = (SerializableMap) getFragmentManager().findFragmentByTag("1234567").getArguments().getSerializable("friendMap");
      //  SerializableMap groupMap = (SerializableMap) getFragmentManager().findFragmentByTag("1234567").getArguments().getSerializable("groupMap");

        //List<String> questName = new ArrayList<String>();
        //String[] questName = new String[] {""};

        questList =  (ListView) view.findViewById(R.id.questList);
    //    SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity(), getData(questMap.getMap()),
    //            R.layout.contacts_questlist, new String[] {"name"}, new int[] {R.id.quest_name});
    //    questList.setAdapter(simpleAdapter);

        return view;
    }

    private List<Map<String, Object>> getData(Map<String, Object> m) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (Map.Entry<String, Object> entry : m.entrySet()) {
            String s = entry.getKey();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name",s);

            list.add(map);
        }

        return list;
    }
}
