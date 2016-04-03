package com.anhlt.maddiscover.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.anhlt.maddiscover.R;
import com.anhlt.maddiscover.services.ApplicationService;

/**
 * Created by anhlt on 2/26/16.
 */


public class BaseFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showErrorDialog(String title, String message){
        ApplicationService.showErrorDialog(title, message, getActivity());
    }

}
