package com.xyz.sharedpreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Lloyd Dcosta
 * This activity is used to save the data into a shared preference that will remember the
 * data even if the app is killed
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PREF_INT_KEY = "PREF_INT_KEY";
    private static final String PREF_STRING_KEY = "PREF_STRING_KEY";
    private EditText mEtSaveNumber;
    private EditText mEtSaveString;
    private Button mBtnSaveNumber;
    private Button mBtnSaveString;
    private Button mBtnGetNumber;
    private Button mBtnGetString;
    private TextView mTvNumber;
    private TextView mTvString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewsAndListeners();
    }

    private void initViewsAndListeners() {
        mEtSaveNumber = findViewById(R.id.etInt);
        mEtSaveString = findViewById(R.id.etString);
        mBtnSaveNumber = findViewById(R.id.btnSaveInt);
        mBtnSaveString = findViewById(R.id.btnSaveString);
        mTvNumber = findViewById(R.id.tvNumber);
        mTvString = findViewById(R.id.tvString);
        mBtnGetNumber = findViewById(R.id.btnGetNumber);
        mBtnGetString = findViewById(R.id.btnGetString);
        mBtnSaveNumber.setOnClickListener(this);
        mBtnSaveString.setOnClickListener(this);
        mBtnGetNumber.setOnClickListener(this);
        mBtnGetString.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnSaveInt:
                if (isNumber())
                    PreferenceHelper.writeIntToPreference(MainActivity.this, PREF_INT_KEY, Integer.parseInt(mEtSaveNumber.getText().toString()));
                break;
            case R.id.btnSaveString:
                PreferenceHelper.writeStringToPreference(MainActivity.this, PREF_STRING_KEY, mEtSaveString.getText().toString());
                break;
            case R.id.btnGetNumber:
                int numberFromPref = PreferenceHelper.getIntFromPreference(MainActivity.this, PREF_INT_KEY);
                mTvNumber.setText(numberFromPref + " ");
                break;
            case R.id.btnGetString:
                String stringFromPref = PreferenceHelper.getStringFromPreference(MainActivity.this, PREF_STRING_KEY);
                mTvString.setText(stringFromPref);
                break;
        }
    }

    /**
     * Check if the entered character is a number or not, if its a number save the number to
     * the preference else show an error
     *
     * @return true if the entered character is a number else false
     */
    private boolean isNumber() {
        boolean isNumber = false;

        if (!mEtSaveNumber.getText().toString().isEmpty()) {
            try {
                Integer.parseInt(mEtSaveNumber.getText().toString());
                isNumber = true;
            } catch (Exception e) {
                isNumber = false;
                mEtSaveNumber.setError("Enter a valid number");
            }
        } else {
            mEtSaveNumber.setError("Field cannot be empty");
        }
        return isNumber;
    }
}