package helper.develop.android.androiddevelophelper;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import helper.develop.android.androiddevelophelper.appinfo.ShowAppInfoActivity;
import helper.develop.android.androiddevelophelper.demos.DemosActivity;
import helper.develop.android.androiddevelophelper.insertcalllog.InsertCallLogActivity;
import helper.develop.android.androiddevelophelper.insertcontacts.InsertContactsActivity;
import helper.develop.android.androiddevelophelper.insertmessage.InsertMessageActivity;
import helper.develop.android.androiddevelophelper.test.TestActivity;
import helper.develop.android.androiddevelophelper.topactivityinfo.ShowTopActivityInfoActivity;

public class DevelopHelperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_develop_helper);
    }

    public void onClickShowAppInfoButton(View view){
        startActivity(new Intent(this, ShowAppInfoActivity.class));
    }

    public void onClickInsertCallLogButton(View view){
        startActivity(new Intent(this, InsertCallLogActivity.class));
    }

    public void onClickInsertContactsButton(View view){
        startActivity(new Intent(this, InsertContactsActivity.class));
    }

    public void onClickInsertMessageButton(View view){
        startActivity(new Intent(this, InsertMessageActivity.class));
    }

    public void onClickShowTopActivityInfoButton(View view){
        startActivity(new Intent(this, ShowTopActivityInfoActivity.class));
    }


    public void onClickTest(View view){
        startActivity(new Intent(this, TestActivity.class));
    }

    public void onClickDemos(View view){
        startActivity(new Intent(this, DemosActivity.class));
    }

}