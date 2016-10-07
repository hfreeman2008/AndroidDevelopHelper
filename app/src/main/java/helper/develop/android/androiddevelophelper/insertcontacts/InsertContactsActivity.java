package helper.develop.android.androiddevelophelper.insertcontacts;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import helper.develop.android.androiddevelophelper.R;

public class InsertContactsActivity extends AppCompatActivity implements View.OnClickListener {

    private String num = "10";
    private String contactNumber = "10086";
    private String contactName = "test";
    private EditText numberEditText = null;
    private EditText contactsNumberEditText = null;
    private EditText contactsNameEditText = null;
    private Button insertContacts = null;
    private InsertContactUtil insertContactUtil = null;

    private  static final int on_click_insert = 0;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == on_click_insert){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int num = Integer.parseInt(numberEditText.getText().toString());
                        String number = contactsNumberEditText.getText().toString();
                        String name = contactsNameEditText.getText().toString();
                        onInsertContact(num,name,number);
                        Toast.makeText(InsertContactsActivity.this,
                                getString(R.string.insert_count_contact,num),
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_contacts);
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        numberEditText = (EditText)findViewById(R.id.numberEditText);
        numberEditText.setText(num);
        contactsNumberEditText = (EditText)findViewById(R.id.contactsNumberEditText);
        contactsNumberEditText.setText(contactNumber);
        contactsNameEditText = (EditText)findViewById(R.id.contactsNameEditText);
        contactsNameEditText.setText(contactName);
        insertContacts = (Button)findViewById(R.id.insertContacts);
        insertContacts.setOnClickListener(this);
        insertContactUtil = new InsertContactUtil(this);
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        if(view == insertContacts){
            handler.sendEmptyMessage(on_click_insert);
        }
    }

    private void onInsertContact(int num, String name, String number) {
        // TODO Auto-generated method stub
        if(num<0 || num > 2000){
            Toast.makeText(this, getString(R.string.toast_number_contact_0_2000), Toast.LENGTH_SHORT).show();
            return;
        }
        if(name.equals(null) || number.equals(null)){
            Toast.makeText(this, getString(R.string.number_or_name_not_null), Toast.LENGTH_SHORT).show();
            return;
        }

        for(int i=0;i<num;i++){
            insertContactUtil.insertContact(name+i,number+i);
        }
    }
}