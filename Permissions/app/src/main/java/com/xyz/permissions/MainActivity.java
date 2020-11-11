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
 * This activity request contacts and location permission on click of respective buttons and
 * handles the permission result
 */
public class MainActivity extends AppCompatActivity {

    private static final int CONTACTS_PERMISSION_REQ_CODE = 101;
    private static final int LOCATION_PERMISSION_REQ_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the buttons are clicked ( in the xml file android:onClick attribute is defined)
     *
     * @param view the view that has been clicked.
     */
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnContactPermission) {
            /*
            isPermissionGranted will check if the contacts permission is already granted by the user , if
            the permission is granted then the value is true else false
             */
            boolean isPermissionGranted = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
            if (!isPermissionGranted) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACTS_PERMISSION_REQ_CODE);

            } else {
                /*
                If the permission is already granted then display the below Toast message
                 */
                displayToastMessage("Contacts Permission already granted");
            }

        } else if (id == R.id.btnLocationPermission) {
            boolean isPermissionGranted = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
            if (!isPermissionGranted) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQ_CODE);
            } else {
                displayToastMessage("Location Permission already granted");
            }
        }
    }

    /**
     * What if you want to handle the permission results like if the user denies the permission you want to do something ?
     * This method is called after the user allows/denies a permission
     *
     * @param requestCode  code to identify what permission has been requested, specified while requesting a permission
     * @param permissions  all the permissions that have been requested
     * @param grantResults tells if the permission has been granted or denied ( if granted the value is 0 , if denied value is -1)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CONTACTS_PERMISSION_REQ_CODE:
                   /*
                grantResults has length 1 as only permission is requested on click of a button, so
                its ok to directly check grantResults[0]. If the permission is granted the value will be 0
                else if the permission is denied by the user value is -1
                 */
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    displayToastMessage("Contacts permission granted");
                } else {
                    displayToastMessage("Contacts permission denied");
                }
                break;
            case LOCATION_PERMISSION_REQ_CODE:
                /*
                grantResults has length 1 as only permission is requested on click of a button, so
                its ok to directly check grantResults[0]. If the permission is granted the value will be 0
                else if the permission is denied by the user value is -1
                 */
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    displayToastMessage("Location permission granted");
                } else {
                    displayToastMessage("Location permission denied");
                }
                break;
        }
    }

    private void displayToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}