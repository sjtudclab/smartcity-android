package sjtu.dclab.smartcity.ui.info_look_up;

import sjtu.dclab.smartcity.webservice.BasicWebService;

/**
 * Created by theGODofws on 2015/8/14.
 */
public class HttpThreadInfoLokUp extends Thread{
    private String url;
    BasicWebService bws;

    public HttpThreadInfoLokUp(String url,BasicWebService bws){
        this.url = url;
        this.bws = bws;
    }

    public String doGet(){
        String json = bws.sendGetRequest(url,null);
        return json;
    }
}