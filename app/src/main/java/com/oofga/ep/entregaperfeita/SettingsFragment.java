package com.oofga.ep.entregaperfeita;//

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

// Created by rhuan on 22/05/19.
// ep2
//
public class SettingsFragment extends Fragment {
    private static EditText edittext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings_fragment, container,false);
        return view;
    }


}
