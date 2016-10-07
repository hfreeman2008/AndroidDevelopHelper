package helper.develop.android.androiddevelophelper.topactivityinfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import helper.develop.android.androiddevelophelper.R;

public class ShowTopActivityInfoActivity extends AppCompatActivity {

    private final static String TAG = "debug_hxm";
    private CheckBox checkBox = null;
    private boolean isCheck = false;
    private EditText editText = null;
    private String time_lapse = "5";
    private TopActivityInfoHelp help = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_top_info);
        init();
    }

    private void init() {
        Log.i(TAG,"TestActivity----init()");

        help = new TopActivityInfoHelp(this);
        isCheck = help.getIsShow();
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setChecked(isCheck);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onDoClick(isChecked);
            }
        });

        editText = (EditText) findViewById(R.id.editText);
        time_lapse = Integer.toString(help.getTimeLapse());
        editText.setText(time_lapse);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG,"beforeTextChanged");
                Log.d(TAG,"beforeTextChanged--s:"+s+"--start:"+start+"--count:"+count+"--after:"+after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG,"onTextChanged");
                Log.d(TAG,"onTextChanged--s:"+s+"--start:"+start+"--before:"+before+"--count:"+count);

                try {
                    help.setTimeLapse(Integer.parseInt(s.toString()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG,"afterTextChanged");
            }
        });

        if(isCheck){
            startService(new Intent(this, ShowTopActivityInfoService.class));
        }
    }

    private void onDoClick(boolean isChecked) {
        help.setIsShow(isChecked);
        checkBox.setChecked(isChecked);
        isCheck = isChecked;
    }
}