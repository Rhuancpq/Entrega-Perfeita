package com.oofga.ep.entregaperfeita;


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

public class RemoveFragment extends Fragment {
    EditText inId;
    Button btnRemoverFrete;

    RemoveListener removeCallback;

    public interface RemoveListener{
        void onBtnRemoverFreteClick(int Id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            removeCallback = (RemoveListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " Deve implementar RemoveListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.remove_fragment, container, false);
        inId = view.findViewById(R.id.inId);
        btnRemoverFrete = view.findViewById(R.id.btnRemoverFrete);
        btnRemoverFrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCallback.onBtnRemoverFreteClick(
                        Integer.parseInt(inId.getText().toString())
                );
            }
        });

        inId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!inId.getText().toString().isEmpty()) {
                    if (Integer.parseInt(inId.getText().toString()) == 0) {
                        btnRemoverFrete.setVisibility(Button.GONE);
                    }else {
                        btnRemoverFrete.setVisibility(Button.VISIBLE);
                    }
                } else {
                    btnRemoverFrete.setVisibility(Button.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

}
