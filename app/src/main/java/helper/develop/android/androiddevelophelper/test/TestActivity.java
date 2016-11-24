package helper.develop.android.androiddevelophelper.test;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import helper.develop.android.androiddevelophelper.R;

public class TestActivity extends AppCompatActivity {

    public static final String TAG = "AsyncTask";

    private Button execute;
    private Button cancel;
    private ProgressBar progressBar;
    private TextView textView;
    private MyTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    private void init() {
        execute = (Button) findViewById(R.id.execute);
        cancel = (Button) findViewById(R.id.cancel);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textView = (TextView) findViewById(R.id.text_view);

        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //208014903848063
        String imsi = tm.getSubscriberId();
        String mcc_mnc = imsi.substring(0,5);
        //46001
        String networkOperator = tm.getNetworkOperator();
        //CHN-UNICOM
        String networkOperatorName = tm.getNetworkOperatorName();

        Log.i(TAG,"imsi:"+imsi);
        Log.i(TAG,"mcc_mnc:"+mcc_mnc);
        Log.i(TAG,"networkOperator:"+networkOperator);
        Log.i(TAG,"networkOperatorName:"+networkOperatorName);



    }

    public void onClickExecute(View view){
        Log.i(TAG, "onClickExecute:");
        mTask = new MyTask();
        mTask.execute("params[0]--start run background logic","params[1]--0");
        execute.setEnabled(false);
        cancel.setEnabled(true);
    }

    public void onClickCancel(View view){
        Log.i(TAG, "onClickCancel:");
        mTask.cancel(true);
    }

    private class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute() called");
            textView.setText("loading...");
        }

        @Override
        protected String doInBackground(String... params) {
            Log.i(TAG, "doInBackground(Params... params) called");

            Log.i(TAG, "doInBackground--params[0]:"+params[0]);
            Log.i(TAG, "doInBackground--params[1]:"+params[1]);

            int progress = 0;

            while (progress <= 100){
                try {
                    Thread.sleep(500);
                    progress = progress + 1;
                    Log.i(TAG, "doInBackground--progress:"+progress);
                    publishProgress(progress);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(progress == 101){
                return "result_success!";
            }else {
                return "result_fail!";
            }

        }

        @Override
        protected void onProgressUpdate(Integer... progresses) {
            Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
            progressBar.setProgress(progresses[0]);
            textView.setText("loading..." + progresses[0] + "%");
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG, "onPostExecute(Result result) called");
            Log.i(TAG, "onPostExecute---result:"+result);
            textView.setText(result);
            execute.setEnabled(true);
            cancel.setEnabled(false);
        }

        @Override
        protected void onCancelled() {
            Log.i(TAG, "onCancelled() called");
            textView.setText("cancelled");
            progressBar.setProgress(0);
            execute.setEnabled(true);
            cancel.setEnabled(false);
        }
    }
}