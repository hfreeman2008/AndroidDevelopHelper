package helper.develop.android.androiddevelophelper.topactivityinfo;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ShowTopActivityInfoService extends Service {

    private final static String TAG = "debug_hxm";
    private int time_lapse = 5;
    private Thread thread = null;
    private TopActivityInfoHelp help = null;

    public ShowTopActivityInfoService() {
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        super.onRebind(intent);
        Log.i(TAG, "onRebind");
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.i(TAG, "onDestroy");

    }

    public class LocalService1Binder extends Binder {
        ShowTopActivityInfoService getService() {
            return ShowTopActivityInfoService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"LocalService1----onCreate");
        if(help == null){
            help = new TopActivityInfoHelp(this);
        }

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(help.getIsShow()){
                        Log.i(TAG,"LocalService1----onCreate--run");
                        help.showTopActivityInfo();
                        try {
                            time_lapse = help.getTimeLapse();
                            if(time_lapse <= 0){
                                time_lapse = 5;
                            }
                            Thread.sleep(time_lapse*1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"LocalService1----onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
}