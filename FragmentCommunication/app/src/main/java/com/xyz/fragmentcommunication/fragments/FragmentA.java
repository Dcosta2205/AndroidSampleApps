package com.xyz.fragmentcommunication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xyz.fragmentcommunication.R;
import com.xyz.fragmentcommunication.interfaces.FragmentListener;

public class FragmentA extends Fragment {
    private Button mBtnPassData;
    private EditText mEtPassData;
    private FragmentListener mFragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        mBtnPassData = view.findViewById(R.id.btnPassData);
        mEtPassData = view.findViewById(R.id.etSendData);
        mBtnPassData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                if (isDataValid() && mFragmentListener != null) {
                    bundle.putString("data", mEtPassData.getText().toString());
                    mFragmentListener.onFragmentDataPassed(bundle);
                }
            }
        });
    }

    /**
     * Validates if the Edit Text is empty or not, if empty sets an error else returns true
     *
     * @return true if edit text is not empty else false
     */
    private boolean isDataValid() {
        if (mEtPassData.getText().toString().isEmpty()) {
            mEtPassData.setError("Data cannot be empty");
            return false;
        }
        return true;
    }

    /**
     * This method is used to set the listener from activity and used to communicate back to the activity
     */
    public void setFragmentListener(FragmentListener fragmentListener) {
        mFragmentListener = fragmentListener;
    }

    /*
     * This method can also be used to get the Fragmment Listener from activity.
     */
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if(context instanceof MainActivity){
//            mFragmentListener = (FragmentListener) context;
//        }
//    }
}