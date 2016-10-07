package helper.develop.android.androiddevelophelper.insertcalllog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import helper.develop.android.androiddevelophelper.R;

public class InsertCallLogActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "InsertCallLogActivity";

    private String num = null;
    private String phoneNumber = null;
    private String type = null;
    private EditText numberEditText = null;
    private EditText phoneNumberEditText= null;
    private Button insertCallLog= null;
    private RadioGroup call_log_type=null;
    private RadioButton outcall=null;
    private RadioButton incall=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_call_log);
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        num = getString(R.string.default_insert_call_log_num);
        phoneNumber = getString(R.string.default_insert_call_log_number);
        type = getString(R.string.default_insert_call_log_type);

        numberEditText = (EditText)findViewById(R.id.number);
        numberEditText.setText(num);
        phoneNumberEditText = (EditText)findViewById(R.id.phoneNumberEditText);
        phoneNumberEditText.setText(phoneNumber);
        insertCallLog = (Button)findViewById(R.id.insertCallLog);
        insertCallLog.setOnClickListener(this);
        call_log_type=(RadioGroup)findViewById(R.id.call_log_type);
        outcall=(RadioButton) findViewById(R.id.outcall);
        incall=(RadioButton) findViewById(R.id.incall);
        call_log_type.setOnCheckedChangeListener(new OnCheckedChangeListenerImp());

        if(type.equals("2")){
            outcall.setChecked(true);
        }else if(type.equals("3")){
            incall.setChecked(true);
        }
    }

    private class OnCheckedChangeListenerImp implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(outcall.getId()==checkedId){
                type="2";
            }
            else if(incall.getId()==checkedId){
                type="3";
            }
        }
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        if(view == insertCallLog){
            num = numberEditText.getText().toString();
            phoneNumber = phoneNumberEditText.getText().toString();
            onInsertCallLog(num, phoneNumber,type);
        }
    }

    private void onInsertCallLog(String num,String phoneNumber,String type) {
        // TODO Auto-generated method stub
        if(Integer.parseInt(num)<0 || Integer.parseInt(num)> 2000){
            Toast.makeText(this, getString(R.string.toast_number_0_2000), Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        InsertCallLogInfoHelper insertCallLogInfoHelper = new InsertCallLogInfoHelper(this);

        CallLogInfo myCallLogInfo;
        String number = phoneNumber;
        String name;
        String duration = "100";
        for(int i=0;i<Integer.parseInt(num);i++){
            number =  Integer.toString((Integer.parseInt(phoneNumber) +i));
            name = "test"+i;
            myCallLogInfo = new CallLogInfo(number, name, duration, type);
            insertCallLogInfoHelper.onAsyncInsertDataLister(myCallLogInfo);
        }
        Toast.makeText(this, getString(R.string.insert_count_call_log,Integer.parseInt(num)), Toast.LENGTH_SHORT).show();
    }
}