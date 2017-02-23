package helper.develop.android.androiddevelophelper.prop;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class PropHelper {

    public static boolean saveToSDCard(String content,String des) {
        boolean result = true;
        FileOutputStream fos = null;
        try {
            File f1 = new File(des);
            if(f1.exists()){
                f1.delete();
            }
            f1.createNewFile();
            fos = new FileOutputStream(f1);
            fos.write(content.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = false;
            return result;
        }finally{
            try {
                if(fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                result = false;
                return result;
            }
        }
        return result;
    }

    public static String execCommand(String command) throws IOException {
        String result = "fail02";
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);
        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
                return result;
            }
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line+" \n");
            }
            result = stringBuffer.toString();
            return result;

        } catch (InterruptedException e) {
            System.err.println(e);
            return  "fail03";
        }
    }

}