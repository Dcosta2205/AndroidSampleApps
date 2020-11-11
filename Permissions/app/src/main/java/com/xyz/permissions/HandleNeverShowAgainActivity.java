package com.xyz.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class HandleNeverShowAgainActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQ_CODE = 1;
    private Button mBtnReqCameraPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_never_show_again);
        mBtnReqCameraPermission = findViewById(R.id.btnReqCameraPermission);
        mBtnReqCameraPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(HandleNeverShowAgainActivity.this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQ_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {

            /*
             * The below code is executed if the user has granted the permission
             */
            if (grantResults[0] > PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                /*
                shouldShowRequestPermissionRationale() function which returns true if the app has requested
                this permission previously and the user denied the request. If the user turned down the permission
                 request in the past and chose the Don't ask again option, this method returns false.

                 */
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,
                            "Permission denied by checking don't show again, go to settings and enable ", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}