package helper.develop.android.androiddevelophelper.appinfo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import helper.develop.android.androiddevelophelper.R;

public class ShowAppInfoActivity extends AppCompatActivity {

    private TextView showAppInfo = null;
    private StringBuilder sb;
    private static final int show_app_info_cm = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == show_app_info_cm){
                showAppInfo.setText(sb);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_app_info);
        init();
    }

    private void init() {
        showAppInfo = (TextView) findViewById(R.id.showAppInfo);
        showAppInfo.setText(getString(R.string.beginCollectAppInfo));
        handler.post(new Runnable() {
            @Override
            public void run() {
                sb = ShowAppInfo.show(ShowAppInfoActivity.this);
                Toast.makeText(ShowAppInfoActivity.this, getString(R.string.show_app_info_ok), Toast.LENGTH_LONG).show();
                handler.sendEmptyMessage(show_app_info_cm);
            }
        });
    }
}