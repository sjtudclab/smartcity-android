package sjtu.dclab.smartcity.ui.info_look_up;

import android.app.Activity;
import android.os.Bundle;

import sjtu.dclab.smartcity.R;

/**
 * Created by 孤独的观测者 on 2015/8/14.
 */
public class SomethingMore extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO:测试阶段先跳转回population_info
        setContentView(R.layout.population_info);
    }
}
