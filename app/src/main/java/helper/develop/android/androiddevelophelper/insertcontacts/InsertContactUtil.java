package helper.develop.android.androiddevelophelper.insertcontacts;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;

public class InsertContactUtil {

    private Context context = null;

    public InsertContactUtil() {
    }

    public InsertContactUtil(Context context) {
        this.context = context;
    }

    public void insertContact(String name, String number) {
        // TODO Auto-generated method stub

        ContentValues values = new ContentValues ();
        Uri rawContactUri = context.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI,values);
        long rawContactsId = ContentUris.parseId(rawContactUri);

        values.clear();
        //A reference to the _ID that this data belongs to
        values.put(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID,rawContactsId);
        //"CONTENT_ITEM_TYPE" MIME type used when storing this in data table
        values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        //The name that should be used to display the contact.
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,name);
        //insert the real values
        context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);

        values.clear();
        values.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID,rawContactsId);
        //String "Data.MIMETYPE":The MIME type of the item represented by this row
        //String "CONTENT_ITEM_TYPE": MIME type used when storing this in data table.
        values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,number);
        context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI,values);
    }

}
