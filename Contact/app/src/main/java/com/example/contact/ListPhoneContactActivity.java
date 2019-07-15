package com.example.contact;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class ListPhoneContactActivity extends AppCompatActivity {

    private List<String> phoneContactsList = new ArrayList<String>();
    private ArrayAdapter<String> contactsListDataAdapter;

    ListView contactsListView = null;

    private int PERMISSION_REQUEST_CODE_READ_CONTACTS = 1;

    private int PERMISSION_REQUEST_CODE_WRITE_CONTACTS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_phone_contact);

        contactsListView = (ListView)findViewById(R.id.display_phone_ocntacts_list_view);
        contactsListDataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, phoneContactsList);
        contactsListView.setAdapter(contactsListDataAdapter);

        Button addPhoneContactsButton = (Button)findViewById(R.id.add_phone_contacts_button);
        addPhoneContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPhoneContactsPermission(Manifest.permission.WRITE_CONTACTS))
                {
                    requestPermission(Manifest.permission.WRITE_CONTACTS, PERMISSION_REQUEST_CODE_WRITE_CONTACTS);
                }else
                {
                    AddPhoneContactActivity.start(getApplicationContext());
                }
            }
        });
        Button readPhoneContactsButton = (Button)findViewById(R.id.read_phone_contacts_button);
        readPhoneContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!hasPhoneContactsPermission(Manifest.permission.READ_CONTACTS))
                {
                    requestPermission(Manifest.permission.READ_CONTACTS, PERMISSION_REQUEST_CODE_READ_CONTACTS);
                }else
                {
                    readPhoneContacts();
                }
            }
        });
    }

    private boolean hasPhoneContactsPermission(String permission)
    {
        boolean ret = false;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int hasPermission = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
            if (hasPermission == PackageManager.PERMISSION_GRANTED) {
                ret = true;
            }
        }else
        {
            ret = true;
        }
        return ret;
    }

    private void requestPermission(String permission, int requestCode)
    {
        String requestPermissionArray[] = {permission};
        ActivityCompat.requestPermissions(this, requestPermissionArray, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int length = grantResults.length;
        if(length > 0)
        {
            int grantResult = grantResults[0];

            if(grantResult == PackageManager.PERMISSION_GRANTED) {

                if(requestCode==PERMISSION_REQUEST_CODE_READ_CONTACTS)
                {
                    readPhoneContacts();
                }else if(requestCode==PERMISSION_REQUEST_CODE_WRITE_CONTACTS)
                {
                    AddPhoneContactActivity.start(getApplicationContext());
                }
            }else
            {
                Toast.makeText(getApplicationContext(), "You denied permission.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void readPhoneContacts()
    {

        int size = phoneContactsList.size();
        for(int i=0;i<size;i++)
        {
            phoneContactsList.remove(i);
            i--;
            size = phoneContactsList.size();
        }

        Uri readContactsUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(readContactsUri, null, null, null, null);

        if(cursor!=null)
        {
            cursor.moveToFirst();

            do{
                int displayNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String userDisplayName = cursor.getString(displayNameIndex);

                int phoneNumberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phoneNumber = cursor.getString(phoneNumberIndex);

                String phoneTypeStr = "Mobile";
                int phoneTypeColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
                int phoneTypeInt = cursor.getInt(phoneTypeColumnIndex);
                if(phoneTypeInt== ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                {
                    phoneTypeStr = "Home";
                }else if(phoneTypeInt== ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                {
                    phoneTypeStr = "Mobile";
                }else if(phoneTypeInt== ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                {
                    phoneTypeStr = "Work";
                }

                StringBuffer contactStringBuf = new StringBuffer();
                contactStringBuf.append(userDisplayName);
                contactStringBuf.append("\r\n");
                contactStringBuf.append(phoneNumber);
                contactStringBuf.append("\r\n");
                contactStringBuf.append(phoneTypeStr);

                phoneContactsList.add(contactStringBuf.toString());
            }while(cursor.moveToNext());
            contactsListDataAdapter.notifyDataSetChanged();
        }
    }
}