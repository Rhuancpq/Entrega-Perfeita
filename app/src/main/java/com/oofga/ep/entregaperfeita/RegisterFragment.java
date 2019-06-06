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

public class RegisterFragment extends Fragment {
    Button btnEncomenda;
    EditText inNome, inDistancia, inTempo, inCarga;

    RegisterListener RegisterCallback;

    interface RegisterListener {
        void onRegisterButtonClick(String name, double distancia, double carga, double tempoMax);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            RegisterCallback = (RegisterListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " Deve implementar RegisterListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        inNome = view.findViewById(R.id.inNome);
        inDistancia = view.findViewById(R.id.inDistancia);
        inTempo = view.findViewById(R.id.inTempo);
        inCarga = view.findViewById(R.id.inCarga);
        inCarga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!inCarga.getText().toString().isEmpty() &&
                        !inTempo.getText().toString().isEmpty() &&
                        !inDistancia.getText().toString().isEmpty() &&
                        !inNome.getText().toString().isEmpty()) {
                    if (Double.parseDouble(inCarga.getText().toString()) == 0d ||
                            Double.parseDouble(inTempo.getText().toString()) == 0d ||
                            Double.parseDouble(inDistancia.getText().toString()) == 0d) {
                        btnEncomenda.setVisibility(Button.GONE);
                    } else {
                        btnEncomenda.setVisibility(Button.VISIBLE);
                    }
                } else {
                    btnEncomenda.setVisibility(Button.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inTempo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!inCarga.getText().toString().isEmpty() &&
                        !inTempo.getText().toString().isEmpty() &&
                        !inDistancia.getText().toString().isEmpty() &&
                        !inNome.getText().toString().isEmpty()) {
                    if (Double.parseDouble(inCarga.getText().toString()) == 0d ||
                            Double.parseDouble(inTempo.getText().toString()) == 0d ||
                            Double.parseDouble(inDistancia.getText().toString()) == 0d) {
                        btnEncomenda.setVisibility(Button.GONE);
                    } else {
                        btnEncomenda.setVisibility(Button.VISIBLE);
                    }
                } else {
                    btnEncomenda.setVisibility(Button.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inDistancia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!inCarga.getText().toString().isEmpty() &&
                        !inTempo.getText().toString().isEmpty() &&
                        !inDistancia.getText().toString().isEmpty() &&
                        !inNome.getText().toString().isEmpty()) {
                    if (Double.parseDouble(inCarga.getText().toString()) == 0d ||
                            Double.parseDouble(inTempo.getText().toString()) == 0d ||
                            Double.parseDouble(inDistancia.getText().toString()) == 0d) {
                        btnEncomenda.setVisibility(Button.GONE);
                    } else {
                        btnEncomenda.setVisibility(Button.VISIBLE);
                    }
                } else {
                    btnEncomenda.setVisibility(Button.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!inCarga.getText().toString().isEmpty() &&
                        !inTempo.getText().toString().isEmpty() &&
                        !inDistancia.getText().toString().isEmpty() &&
                        !inNome.getText().toString().isEmpty()) {
                    if (Double.parseDouble(inCarga.getText().toString()) == 0d ||
                            Double.parseDouble(inTempo.getText().toString()) == 0d ||
                            Double.parseDouble(inDistancia.getText().toString()) == 0d) {
                        btnEncomenda.setVisibility(Button.GONE);
                    } else {
                        btnEncomenda.setVisibility(Button.VISIBLE);
                    }
                } else {
                    btnEncomenda.setVisibility(Button.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Button
        btnEncomenda = view.findViewById(R.id.btnEncomenda);
        btnEncomenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButtonClick(v);
            }
        });
        return view;
    }

    private void registerButtonClick(View v) {
        RegisterCallback.onRegisterButtonClick(inNome.getText().toString(),
                Double.parseDouble(inDistancia.getText().toString()),
                Double.parseDouble(inCarga.getText().toString()),
                Double.parseDouble(inTempo.getText().toString()));
    }

}
