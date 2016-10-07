package helper.develop.android.androiddevelophelper.insertcalllog;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.util.Log;

public class InsertCallLogInfoHelper {
    public static final String TAG = "InsertCallLogInfoHelper";

    private Context myContext = null;
    private AsyncQueryHandler asyncQuery = null;

    private String[] CALL_LOG_PROJECTION = new String[] {
            CallLog.Calls._ID,
            CallLog.Calls.NUMBER,
            CallLog.Calls.CACHED_NAME,
            CallLog.Calls.DURATION,
            CallLog.Calls.TYPE
    };

    public InsertCallLogInfoHelper(Context myContext){
        this.myContext = myContext;
        this.asyncQuery = new MyAsyncQueryHandler(myContext.getContentResolver());
    }

    private class MyAsyncQueryHandler extends AsyncQueryHandler {

        public MyAsyncQueryHandler(ContentResolver cr) {
            super(cr);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onDeleteComplete(int token, Object cookie, int result) {
            // TODO Auto-generated method stub
            Log.i(TAG, "MyAsyncQueryHandler--onDeleteComplete");
            super.onDeleteComplete(token, cookie, result);
        }

        @Override
        protected void onInsertComplete(int token, Object cookie, Uri uri) {
            // TODO Auto-generated method stub
            Log.i(TAG, "MyAsyncQueryHandler--onInsertComplete");
            super.onInsertComplete(token, cookie, uri);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            // TODO Auto-generated method stub
            Log.i(TAG, "MyAsyncQueryHandler--onQueryComplete");
            super.onQueryComplete(token, cookie, cursor);
        }

        @Override
        protected void onUpdateComplete(int token, Object cookie, int result) {
            // TODO Auto-generated method stub
            Log.i(TAG, "MyAsyncQueryHandler--onUpdateComplete");
            super.onUpdateComplete(token, cookie, result);
        }
    }

    public void onAsyncQueryDataLister() {
        // TODO Auto-generated method stub
        Log.i(TAG, "Controller--onAsyncQueryDataLister");
        asyncQuery.startQuery(0,
                null,
                CallLog.Calls.CONTENT_URI,
                CALL_LOG_PROJECTION,
                null,
                null,
                CallLog.Calls.DEFAULT_SORT_ORDER);
    }

    public void onAsyncInsertDataLister(CallLogInfo myCallLogInfo) {
        // TODO Auto-generated method stub
        Log.i(TAG, "Controller--onAsyncInsertDataLister");
        ContentValues values = new ContentValues();
        values.put(CALL_LOG_PROJECTION[1], myCallLogInfo.getNumber());
        values.put(CALL_LOG_PROJECTION[2], myCallLogInfo.getName());
        values.put(CALL_LOG_PROJECTION[3], myCallLogInfo.getDuration());
        values.put(CALL_LOG_PROJECTION[4], myCallLogInfo.getType());
        asyncQuery.startInsert(0, null, CallLog.Calls.CONTENT_URI, values);
    }

    public void onAsyncDeleteDataLister(String where, String[] deleteValue) {
        // TODO Auto-generated method stub
        Log.i(TAG, "Controller--onAsyncDeleteDataLister");
        asyncQuery.startDelete(0, null, CallLog.Calls.CONTENT_URI, where+"=?", deleteValue);
    }

    public void onAsyncUpdateDataLister(ContentValues values, String where, String[] selectValue) {
        // TODO Auto-generated method stub
        Log.i(TAG, "Controller--onAsyncUpdateDataLister");
        asyncQuery.startUpdate(0, null, CallLog.Calls.CONTENT_URI, values, where+"=?", selectValue);
    }
}
