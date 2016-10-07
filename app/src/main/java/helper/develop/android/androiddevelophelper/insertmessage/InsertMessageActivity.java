package helper.develop.android.androiddevelophelper.insertmessage;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
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

public class InsertMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG ="InsertMessageActivity";

    private final static int INSERT_MESSAGE_OK = 1;
    private final static int INSERT_MESSAGE = 2;

    private final String ADDRESS = "address";
    private final String DATE = "date";
    private final String READ = "read";
    private final String STATUS = "status";
    private final String TYPE = "type";
    private final String BODY = "body";

    private String mess_account = "10";
    private String addressTem = "10086";
    private String content_mess;
    private int messageType = 2;
    private boolean messageRead = true;

    private EditText numberMesEditText;
    private EditText addressEditText;
    private EditText bodyEditText;
    private Button insertMessageButton;
    private RadioGroup message_type = null;
    private RadioButton outbox = null;
    private RadioButton inbox = null;
    private RadioGroup message_read = null;
    private RadioButton read = null;
    private RadioButton unread = null;

    private InsertMessageHelper insertMessageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_message);
        init();
    }


    private void init() {
        // TODO Auto-generated method stub
        numberMesEditText = (EditText)findViewById(R.id.numberMesEditText);
        numberMesEditText.setText(mess_account);
        addressEditText = (EditText)findViewById(R.id.addressEditText);
        addressEditText.setText(addressTem);
        bodyEditText = (EditText)findViewById(R.id.bodyEditText);
        bodyEditText.setHint(getString(R.string.mes_hint));
        insertMessageButton = (Button)findViewById(R.id.insertMessageButton);
        insertMessageButton.setOnClickListener(this);
        message_type=(RadioGroup)findViewById(R.id.message_type);
        outbox=(RadioButton) findViewById(R.id.outbox);
        inbox=(RadioButton) findViewById(R.id.inbox);
        message_type.setOnCheckedChangeListener(new OnCheckedChangeListenerImpMessageType());
        message_read=(RadioGroup)findViewById(R.id.message_read);
        read=(RadioButton) findViewById(R.id.read);
        unread=(RadioButton) findViewById(R.id.unread);
        message_read.setOnCheckedChangeListener(new OnCheckedChangeListenerImpMessageRead());
        insertMessageHelper = new InsertMessageHelper(this);
    }


    private class OnCheckedChangeListenerImpMessageType implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(outbox.getId()==checkedId){
                messageType=2;
            }
            else if(inbox.getId()==checkedId){
                messageType=1;
            }
        }
    }


    private class OnCheckedChangeListenerImpMessageRead implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(read.getId()==checkedId){
                messageRead=true;
            }
            else if(unread.getId()==checkedId){
                messageRead=false;
            }
        }
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        if(view == insertMessageButton){
            handler.sendEmptyMessage(INSERT_MESSAGE);

        }
    }

    private void onInserMessage() {
        mess_account = numberMesEditText.getText().toString();
        addressTem = addressEditText.getText().toString();
        content_mess = bodyEditText.getText().toString();

        if(Integer.parseInt(mess_account)<0 || Integer.parseInt(mess_account) > 2000){
            Toast.makeText(this, getString(R.string.toast_number_message_0_2000), Toast.LENGTH_SHORT).show();
            return;
        }

        if(addressTem.equals(null) || content_mess.equals(null)){
            Toast.makeText(this, getString(R.string.number_or_content_not_null), Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d(TAG,"mess_account:"+mess_account+"--addressTem"+addressTem+"--messageType"+messageType
                +"--content_mess"+content_mess+"--System.currentTimeMillis()"+System.currentTimeMillis()
                +"--messageRead"+messageRead);

        for(int i=0;i<Integer.parseInt(mess_account);i++){
            Uri uri = insertMessageHelper.insertMessage(
                    addressTem+i,
                    messageType,
                    content_mess,
                    System.currentTimeMillis(),
                    true,
                    messageRead,
                    null);
            Log.d(TAG,"uri"+uri);
        }
    }

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if(msg.what == INSERT_MESSAGE_OK){
                Toast.makeText(InsertMessageActivity.this,
                        getString(R.string.insert_count_message,Integer.parseInt(mess_account)),
                        Toast.LENGTH_LONG).show();
            }
            if(msg.what == INSERT_MESSAGE){
                onInserMessage();
                handler.sendEmptyMessage(INSERT_MESSAGE_OK);
            }
        }
    };
}