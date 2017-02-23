package helper.develop.android.androiddevelophelper.prop;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import helper.develop.android.androiddevelophelper.R;

public class PropActivity extends AppCompatActivity {

    private static final String TAG = "test_prop";

    private EditText editText = null;
    private TextView textView = null;
    private Button button = null;
    private static boolean isSave = false;
    private String commandPro ="/system/bin/getprop";
    private static final String OUTPUT_FILE =
            Environment.getExternalStorageDirectory().toString() + "/prop_all.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop);
        init();
    }

    private void init() {
        editText = (EditText) findViewById(R.id.edit_text);
        textView = (TextView) findViewById(R.id.text_view);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prop = editText.getText().toString();
                if(prop.length() <= 0){
                    isSave = true;
                }else{
                    isSave = false;
                }
                String command = commandPro +" " +prop;
                Log.i(TAG,"init()--command:"+command);
                String restul = "fail01";
                try {
                    restul = PropHelper.execCommand(command);
                    textView.setText(restul);
                    Log.i(TAG,"restul:"+restul);
                    if(isSave){
                        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                            boolean success = PropHelper.saveToSDCard(restul,OUTPUT_FILE);
                            if(success){
                                Toast.makeText(
                                        getApplicationContext(),
                                        getString(R.string.save_sdcard_success),
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        getString(R.string.save_sdcard_fail),
                                        Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(
                                    getApplicationContext(),
                                    getString(R.string.save_sdcard_fail),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    textView.setText(restul);
                }
            }
        });
    }

}