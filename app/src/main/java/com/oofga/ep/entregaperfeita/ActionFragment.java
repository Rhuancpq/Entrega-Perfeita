package com.oofga.ep.entregaperfeita;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActionFragment extends Fragment {
    private Spinner spinner;
    private Button btnAction;
    private String tipoSelecionado, tipoAcao;
    private EditText inQuantidade;

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    ActionListener activityCallback;

    public interface ActionListener {
        public void onActionButtonClick(String tipo, int Quantidade, String acao);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCallback = (ActionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " Deve implementar ActionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.action_fragment, container, false);
        spinner = view.findViewById(R.id.spinnerTipo);
        btnAction = view.findViewById(R.id.btnAction);
        inQuantidade = view.findViewById(R.id.inQuantidade);
        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.veiculos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                tipoSelecionado = (String) parent.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView parent) {
                tipoSelecionado = "Carreta";
                String text = "Nenhum tipo selecionado, Carreta selecionada por padrão";
                Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_LONG);
                toast.show();
            }

        });
        //Button
        btnAction.setText(tipoAcao);
        btnAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actionButtonClicked(v);
            }
        });
        //EditText
        inQuantidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!inQuantidade.getText().toString().isEmpty()) {
                    if (Integer.parseInt(inQuantidade.getText().toString()) == 0) {
                        btnAction.setVisibility(Button.GONE);
                    }else {
                        btnAction.setVisibility(Button.VISIBLE);
                    }
                } else {
                    btnAction.setVisibility(Button.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    public void actionButtonClicked(View view) {
        try {
            activityCallback.onActionButtonClick(tipoSelecionado,
                    Integer.parseInt((inQuantidade.getText().toString())), tipoAcao);
        } catch (NumberFormatException e) {
            String text = "Quantidade muito alta!!";
            Toast toast = Toast.makeText(getActivity(),text, Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
