package com.example.sumeet.contentproviderdemoyoutube;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    TextView textView;
    Button button;
    String TAG = "Show";

    private String mColumnProjection[] = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER};

    private String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + "='Simpi'";
    private String[] mSelectionArguments = new String[]{"Simpi"};
    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_show);
        button = (Button) findViewById(R.id.load);
        //   ContentResolver contentResolver = getContentResolver();
//        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
//                mColumnProjection,
//                mSelectionClause,
//                null,
//                null);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.load:
                getLoaderManager().initLoader(1, null, this).forceLoad();
                Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            Toast.makeText(this, "Inside onloader", Toast.LENGTH_SHORT).show();
            return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        }

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Toast.makeText(this, "OnFinish", Toast.LENGTH_SHORT).show();
        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder stringBuilder = new StringBuilder("");
            while (cursor.moveToNext()) {
                stringBuilder.append(cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2) + "\n");
            }
            textView.setText(stringBuilder.toString());
        } else {
            textView.setText("No Contacts to display");
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Toast.makeText(this, "onLoader", Toast.LENGTH_SHORT).show();
    }
}
