package com.xyz.edittextsample;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText mEtUsername; // Declare an object of type EditText
    private Button mBtnGo;
    private String editTextData;
    private TextWatcher usernameListener = new TextWatcher() {
        /*
        This method is called to notify you that, within charSequence, the count characters beginning at start are
        about to be replaced by new text with length after. It is an error to attempt to make changes to charSequence from this callback.
         */
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            Log.d(TAG, "before " + charSequence.toString() + " start " + start + " count " + count + " after " + after);
        }

        /*
        This method is called to notify you that, within charSequence, the count characters beginning at start have
         just replaced old text that had length before. It is an error to attempt to make changes to charSequence from this callback.
         */
        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            Log.d(TAG, "after " + charSequence.toString() + " start " + start + " count " + count + " before " + before);
        }

        /*
        This method is called to notify you that, somewhere within s, the text has been changed.
        It is legitimate to make further changes to s from this callback, but be careful not to get yourself
         into an infinite loop, because any changes you make will cause this method to be called again recursively.
         */
        @Override
        public void afterTextChanged(Editable s) {
            editTextData = s.toString();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mEtUsername = findViewById(R.id.etUsername);
        mBtnGo = findViewById(R.id.btnGo);
        mEtUsername.addTextChangedListener(usernameListener); // Add a text listener here that monitors the edittext events
        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, editTextData);
            }
        });
    }
}