package com.anhlt.maddiscover.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.anhlt.maddiscover.R;

/**
 * Created by anhlt on 2/26/16.
 */


public class BaseFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showErrorDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
//            builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
