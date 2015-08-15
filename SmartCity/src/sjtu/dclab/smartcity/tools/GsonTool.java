package sjtu.dclab.smartcity.tools;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Yang on 2015/7/9.
 */

public class GsonTool {

    public GsonTool() {

    }

    /*
     * 查看Gson api其中的fromJson(String json, Class<T> classOfT)方法
     * public <T> T fromJson(String json, Class<T> classOfT)
     * 用来描述一个特殊的Json对象,使用反射机制可以将JSON字符串中包含的内容赋值给指定的类。这边的T表示的是通过泛型,也就是可以表示任意的类型。
     * 参数说明：
     * json : 指定对象解析过的JSON字符串源,用来转换回Java对象。
     * classOfT ： 泛型   T 的Class对象。
     */
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

    /*
     * List 我们使用的Gson中的
     * public <T> T fromJson(String json, Type typeOfT)
     * 这边我们需要取到List<T>中不同的对象，普通的实现方式就如上一讲中org.Json库来解析JSON的方式一样(读者阅读上一讲内容)，
     * 这里我们通过 Gson中的 TypeToken类是简便操作：这边typeOfT的用法是通过反射机制把T里面的对象的属性的值映射出来，然后通过将json字符串取得的值赋值上去就可以了。
     * getType()的意思就是表示将jsonString的字符串解析出来之后封装到List集合中，然后分别从T里面取出类型将其复制。
     */

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