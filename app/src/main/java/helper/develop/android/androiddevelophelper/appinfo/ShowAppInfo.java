package helper.develop.android.androiddevelophelper.appinfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShowAppInfo {

    private static final String TAG = "debug_hxm";

    public static final int INSTALL_LOCATION_UNSPECIFIED = -1;
    public static final int INSTALL_LOCATION_AUTO = 0;
    public static final int INSTALL_LOCATION_INTERNAL_ONLY = 1;
    public static final int INSTALL_LOCATION_PREFER_EXTERNAL = 2;

    public static StringBuilder show(Context context) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        List<ApplicationInfo> apps = context.getPackageManager().getInstalledApplications(
                PackageManager.GET_UNINSTALLED_PACKAGES);
        for (int i=0;i<apps.size();i++) {
            Log.i(TAG, "---------第"+i+"个app------");
            sb.append("---------第"+i+"个app------");
            sb.append('\n');

            Log.i(TAG, "app.sourceDir:"+apps.get(i).sourceDir);
            sb.append("app.sourceDir:"+apps.get(i).sourceDir);
            sb.append('\n');

            File apk = new File(apps.get(i).sourceDir);
            String length = formatFileLength(apk.length());
            Log.i(TAG, apps.get(i).sourceDir+": "+length);
            sb.append(apps.get(i).sourceDir+" :"+length);
            sb.append('\n');

            Log.i(TAG, "dataDir:"+apps.get(i).dataDir);
            sb.append("dataDir:"+apps.get(i).dataDir);
            sb.append('\n');

//            File dataDir = new File(apps.get(i).dataDir+"/");
//            long dataDirLength = getFolderSize(dataDir);
//            length = formatFileLength(dataDirLength);
//            Log.i(TAG,apps.get(i).dataDir+": "+length);
//            sb.append(apps.get(i).dataDir+" :"+length);
//            sb.append('\n');


            Log.i(TAG, "name:"+apps.get(i).name);
            sb.append("name:"+apps.get(i).name);
            sb.append('\n');

            Log.i(TAG, "className:"+apps.get(i).className);
            sb.append("className:"+apps.get(i).className);
            sb.append('\n');

            Log.i(TAG, "packageName:"+apps.get(i).packageName);
            sb.append("packageName:"+apps.get(i).packageName);
            sb.append('\n');

            Log.i(TAG, "nativeLibraryDir:"+apps.get(i).nativeLibraryDir);
            sb.append("nativeLibraryDir:"+apps.get(i).nativeLibraryDir);
            sb.append('\n');

            Log.i(TAG, "backupAgentName:"+apps.get(i).backupAgentName);
            sb.append("backupAgentName:"+apps.get(i).backupAgentName);
            sb.append('\n');

            Log.i(TAG, "permission:"+apps.get(i).permission);
            sb.append("permission:"+apps.get(i).permission);
            sb.append('\n');

            Log.i(TAG, "processName:"+apps.get(i).processName);
            sb.append("processName:"+apps.get(i).processName);
            sb.append('\n');

            Log.i(TAG, "publicSourceDir:"+apps.get(i).publicSourceDir);
            sb.append("publicSourceDir:"+apps.get(i).publicSourceDir);
            sb.append('\n');

            Log.i(TAG, "taskAffinity:"+apps.get(i).taskAffinity);
            sb.append("taskAffinity:"+apps.get(i).taskAffinity);
            sb.append('\n');

            Log.i(TAG, "flags:"+apps.get(i).flags);
            sb.append("flags:"+apps.get(i).flags);
            sb.append('\n');

            Log.i(TAG, "app is debugable:" +((apps.get(i).flags
                    & ApplicationInfo.FLAG_DEBUGGABLE) == ApplicationInfo.FLAG_DEBUGGABLE));
            sb.append("app is debugable:" +((apps.get(i).flags
                    & ApplicationInfo.FLAG_DEBUGGABLE) == ApplicationInfo.FLAG_DEBUGGABLE));
            sb.append('\n');

            PackageInfo info = null;
            try {
                info = context.getPackageManager().getPackageInfo(apps.get(i).packageName, 0);
                String appVersionName = info.versionName;
                //int currentVersionCode = info.versionCode;
                Log.i(TAG, "appVersion:"+appVersionName);
                sb.append("appVersion:"+appVersionName);
                sb.append('\n');

                Log.i(TAG, "info.versionCode:"+info.versionCode);
                sb.append("info.versionCode:"+info.versionCode);
                sb.append('\n');

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                String firstInstallTime=format.format(info.firstInstallTime);
                Log.i(TAG, "firstInstallTime:"+firstInstallTime);
                sb.append("firstInstallTime:"+firstInstallTime);
                sb.append('\n');

                String lastUpdateTime=format.format(info.lastUpdateTime);

                Log.i(TAG, "lastUpdateTime:"+lastUpdateTime);
                sb.append("lastUpdateTime:"+lastUpdateTime);
                sb.append('\n');

                String install_location ="";
                switch (info.installLocation){
                    case INSTALL_LOCATION_UNSPECIFIED:
                        install_location = "INSTALL_LOCATION_UNSPECIFIED";
                        break;
                    case INSTALL_LOCATION_AUTO:
                        install_location = "INSTALL_LOCATION_AUTO";
                        break;
                    case INSTALL_LOCATION_INTERNAL_ONLY:
                        install_location = "INSTALL_LOCATION_INTERNAL_ONLY";
                        break;
                    case INSTALL_LOCATION_PREFER_EXTERNAL:
                        install_location = "INSTALL_LOCATION_PREFER_EXTERNAL";
                        break;
                }
                Log.i(TAG, "info.installLocation():"+install_location);
                sb.append("info.installLocation:"+install_location);
                sb.append('\n');

            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            sb.append("-------------------------------------------------");
            sb.append('\n');
        }
        return  sb;
    }


    public static String formatFileLength(long length){
        if(length < 1024){
            return length + "B";
        }
        long showLength = length/1024;
        if(showLength < 1024){
            return showLength + "K";
        }else{
            showLength = showLength /1024;
            if(showLength < 1024){
                return showLength + "M";
            }else{
                showLength = showLength /1024;
                return showLength + "G";
            }
        }
    }

    /**
     * 获取文件夹大小
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file){

        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++)
            {
                if (fileList[i].isDirectory())
                {
                    size = size + getFolderSize(fileList[i]);

                }else{
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return size;
    }
}