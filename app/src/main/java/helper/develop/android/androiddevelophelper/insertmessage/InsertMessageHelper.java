package helper.develop.android.androiddevelophelper.insertmessage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Binder;
import android.text.TextUtils;
import android.util.Log;

public class InsertMessageHelper {

    private static final String TAG = "InsertMessageHelper";

    private Context context = null;

    public InsertMessageHelper() {
    }

    public InsertMessageHelper(Context context) {
        this.context = context;
    }

    public Uri insertMessage(String address,
                              int type,
                              String body,
                              long timestampMillis,
                              boolean seen,
                              boolean read,
                              String creator) {

        Uri insertUri = Uri.parse("content://sms/inbox");
        //Uri insertUri = Uri.parse("content://sms/");

        final ContentValues values = new ContentValues();
        values.put("address", address);
        values.put("date", timestampMillis);
        //values.put("seen", seen ? 1 : 0);
        values.put("read", read ? 1 : 0);
        values.put("body", body);

        if (!TextUtils.isEmpty(creator)) {
            values.put("creator", creator);
        }
        final long identity = Binder.clearCallingIdentity();
        try {
            return context.getContentResolver().insert(insertUri, values);
        } catch (SQLiteException e) {
            Log.e(TAG, "importTextMessage: failed to persist imported text message", e);
        } finally {
            Binder.restoreCallingIdentity(identity);
        }
        return null;
    }

    private void readMessage() {
        // TODO Auto-generated method stub
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        while(cursor.moveToNext()) {
            int phoneColumn = cursor.getColumnIndex("address");
            int smsColumn = cursor.getColumnIndex("body");
            Log.i(TAG, "address:"+cursor.getString(phoneColumn)+"---body:"+cursor.getString(smsColumn)+""+"\n");
        }
    }
}