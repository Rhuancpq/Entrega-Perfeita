package com.oofga.ep.entregaperfeita;//

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

// Created by rhuan on 22/05/19.
// ep2
//
public class SettingsFragment extends Fragment {
    EditText inMargem;
    SettingsListener activityCallback;
    public interface SettingsListener {
        public void onSettingsButtonClick(double margem);
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
        inMargem = view.findViewById(R.id.inMargem);
        final Button button =
                (Button) view.findViewById(R.id.buttonOk);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        inMargem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!inMargem.getText().toString().isEmpty()) {
                    final double temp = Double.parseDouble(inMargem.getText().toString());
                    if (temp == 0d || temp == 100) {
                        button.setVisibility(Button.GONE);
                    } else {
                        button.setVisibility(Button.VISIBLE);
                    }
                } else {
                    button.setVisibility(Button.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    public void buttonClicked (View view) {
        activityCallback.onSettingsButtonClick(Double.parseDouble(inMargem.getText().toString()));
    }


}
