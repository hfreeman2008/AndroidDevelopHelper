package helper.develop.android.androiddevelophelper.topactivityinfo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import java.util.List;

public class TopActivityInfoHelp {

    private final static String TAG = "debug_hxm";

    private String xml = "config";
    private static final String KEY_IS_CHECK = "is_check";
    private static final String KEY_TIME_LAPSE = "time_lapse";
    private int time_lapse = 5;
    private boolean is_check = false;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor  editor;

    public TopActivityInfoHelp() {
    }

    public TopActivityInfoHelp(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(xml,0);
        this.editor = sharedPreferences.edit();
    }

    public boolean getIsShow(){
        return sharedPreferences.getBoolean(KEY_IS_CHECK,is_check);
    }

    public void setIsShow(boolean isShow){
        editor.putBoolean(KEY_IS_CHECK,isShow);
        editor.commit();
    }

    public int getTimeLapse(){
        return sharedPreferences.getInt(KEY_TIME_LAPSE,time_lapse);
    }

    public void setTimeLapse(int timeLapse){
        editor.putInt(KEY_TIME_LAPSE,timeLapse);
        editor.commit();
    }

    public void showTopActivityInfo() {
        Log.i(TAG, "showTopActivityInfo()");
        if (Build.VERSION.SDK_INT <= 20) {
            Log.i(TAG, "Build.VERSION.SDK_INT <= 20");
            ActivityManager manager = (ActivityManager)context.getApplicationContext()
                    .getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
            String shortClassName = info.topActivity.getShortClassName();    //类名
            String className = info.topActivity.getClassName();              //完整类名
            String packageName = info.topActivity.getPackageName();          //包名
            String topActivity = info.topActivity.toString();
            Log.i(TAG, "shortClassName:"+shortClassName);
            Log.i(TAG, "className:"+className);
            Log.i(TAG, "packageName:"+packageName);
            Log.i(TAG, "topActivity:"+topActivity);
        }else{
            ActivityManager manager = (ActivityManager) context.getApplicationContext()
                    .getSystemService(Context.ACTIVITY_SERVICE);
            Log.i(TAG, "Build.VERSION.SDK_INT > 20");
            List<ActivityManager.RunningAppProcessInfo> procs = manager.getRunningAppProcesses();
            if(procs != null ){
                Log.i(TAG, "procs.size():"+procs.size());
                for (ActivityManager.RunningAppProcessInfo proc : procs) {
                    //if (proc.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(TAG, "proc.processName:"+proc.processName);
                    Log.i(TAG, "proc.pid:"+proc.pid);
                    Log.i(TAG, "proc.importance:"+proc.importance);
                    Log.i(TAG, "proc.importanceReasonCode:"+proc.importanceReasonCode);

                    //}
                }
            }
        }
    }

}
