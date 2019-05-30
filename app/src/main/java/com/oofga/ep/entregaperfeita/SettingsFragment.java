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

    SettingsListener activityCallback;
    public interface SettingsListener {
        public void onButtonClick();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCallback = (SettingsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " Deve implementar SettingsListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settings_fragment, container,false);
        final Button button =
                (Button) view.findViewById(R.id.buttonOk);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });
        return view;
    }

    public void buttonClicked (View view) {
        activityCallback.onButtonClick();
    }


}
