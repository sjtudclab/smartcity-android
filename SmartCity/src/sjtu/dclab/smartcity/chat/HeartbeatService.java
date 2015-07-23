package sjtu.dclab.smartcity.chat;

import java.util.List;

import cn.edu.sjtu.se.dclab.config.Me;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 2015年6月14日 下午12:48:38
 *
 * @author changyi yuan
 */
public class HeartbeatService extends Service implements Runnable {

	private Heartbeat heartbeat = null;
	
	private Thread thread = null;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		heartbeat = new Heartbeat();
		heartbeat.setClientId(Configurations.clientId);
		heartbeat.setUserId(Me.id);
		heartbeat.setCommand(Configurations.heartbeatCommand);
		thread = new Thread(this);
		thread.start();
		
		super.onStart(intent, startId);
	}

	@Override
	public void run() {
		while (true) {
			/*//判断应用是否运行
			ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningTaskInfo> infos = am.getRunningTasks(3);
			for(RunningTaskInfo info : infos){
				if(info.topActivity.getPackageName().equals("cn.edu.sjtu.se.dclab.community_chat"))
					break;
			}*/
			
			try {
				Publisher.publicHeart(heartbeat);
				Thread.sleep(Configurations.heartbeatPeriod * 1000);
			} catch (Exception e) {
				Log.e("HeartbeatService thread exception", e.getMessage());
			}
		}
	}
	
	

}
