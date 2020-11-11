package com.xyz.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * This activity handles multiple permissions that are requested by the user at once and also shows how to handle the result
 */
public class RequestMultiplePermissionsActivity extends AppCompatActivity {

    private static final int REQ_MULTIPLE_PERMISSIONS_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_multiple_permissions);
    }

    /**
     * This method is called on click of the button
     */
    public void onClick(View view) {
        /*
        Here we are passing a String array of permissions there by requesting multiple permissions at once
         */
        ActivityCompat.requestPermissions(RequestMultiplePermissionsActivity.this,
                new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQ_MULTIPLE_PERMISSIONS_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQ_MULTIPLE_PERMISSIONS_CODE) {

            /*
            grantResults length is 2 as it has contacts and storage permissions
            grantResults[0] has the result for contacts permissions as it is requested first
            grantResults[1] has the result for storage permissions as it is requested after contacts.
             */
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                displayToastMessage("Granted both permissions");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_DENIED) {
                displayToastMessage("Contacts permissions granted, storage permission denied");
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                displayToastMessage("contacts permission denied , storage permission granted");
            } else {
                displayToastMessage("denied both the permissions");
            }
        }
    }

    private void displayToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}