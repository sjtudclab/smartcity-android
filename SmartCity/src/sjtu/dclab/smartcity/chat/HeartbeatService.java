package sjtu.dclab.smartcity.chat;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import sjtu.dclab.smartcity.community.config.Me;

/**
 * 2015年6月14日 下午12:48:38
 *
 * @author changyi yuan
 */
public class HeartbeatService extends Service implements Runnable {

	private Heartbeat heartbeat = null;
	private Thread thread = null;

	private final IBinder binder = new HeartBeatBinder();

	public class HeartBeatBinder extends Binder{
		public HeartbeatService getService(){
			return HeartbeatService.this;
		}
	}


	@Override
	public IBinder onBind(Intent intent) {
		return binder;
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
				Log.e("HBS thread exception", e.getMessage());
			}
		}
	}
}
