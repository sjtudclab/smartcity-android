package sjtu.dclab.smartcity.tools;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Yang on 2015/7/9.
 */

public class GsonTool {

    public GsonTool() {}

    // 将json解析为给定类实例
    public static <T> T getObject(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return t;
    }

    // 将json解析为给定类实例list
    public static <T> List<T> getObjectList(String jsonString, Class<T[]> cls) {
        try {
            Gson gson = new Gson();
            T[] arr = gson.fromJson(jsonString, cls);
            return Arrays.asList(arr);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

//    public static List<ApplicationTransfer> getFriendApplicationTransferList(String content) {
//        Gson gson = new Gson();
//        try {
//            Type type = new TypeToken<List<ApplicationTransfer>>() {
//            }.getType();
//            if (content != null) {
//                List<ApplicationTransfer> record = gson.fromJson(content, type);
//                return record;
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//            return null;
//        }
//    }
}