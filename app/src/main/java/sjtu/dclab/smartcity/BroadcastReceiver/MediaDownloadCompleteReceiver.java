package sjtu.dclab.smartcity.BroadcastReceiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import sjtu.dclab.smartcity.tools.FileOpener;

/**
 * Created by Yang on 2015/11/10.
 */
public class MediaDownloadCompleteReceiver extends BroadcastReceiver {
    private Context context;
    private DownloadManager downloadManager;
    final private String TAG = "MediaCompleteReceiver";

    public MediaDownloadCompleteReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            Toast.makeText(context, "download complete", Toast.LENGTH_SHORT).show();
            Bundle extras = intent.getExtras();
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Query q = new DownloadManager.Query();
            q.setFilterById(extras.getLong(DownloadManager.EXTRA_DOWNLOAD_ID));
            Cursor c = downloadManager.query(q);
            if (c.moveToFirst()){
                int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                if (status == DownloadManager.STATUS_SUCCESSFUL){
                    String filePath = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                    Intent fileIntent = FileOpener.openFile(filePath);
                    context.startActivity(fileIntent);
                }
            }
            c.close();
        }
    }
}
