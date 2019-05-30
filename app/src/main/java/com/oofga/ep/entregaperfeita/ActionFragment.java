package com.oofga.ep.entregaperfeita;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActionFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Button btnAction;
    private String tipoSelecionado,tipoAcao;
    private EditText inQuantidade;

    public void setTipoAcao(String tipoAcao){
        this.tipoAcao = tipoAcao;
    }

    ActionListener activityCallback;
    public interface ActionListener{
        public void onActionButtonClick(String tipo, int Quantidade);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            activityCallback = (ActionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " Deve implementar ActionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.action_fragment, container,false);
        spinner = view.findViewById(R.id.spinnerTipo);
        btnAction = view.findViewById(R.id.btnAction);
        inQuantidade = view.findViewById(R.id.inQuantidade);
        //Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.veiculos,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //Button
        btnAction.setText(tipoAcao);
        btnAction.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actionButtonClicked(v);
            }
        });
        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        tipoSelecionado = (String) parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        tipoSelecionado = "Carreta";
    }

    public void actionButtonClicked(View view){
        try {
            activityCallback.onActionButtonClick(tipoSelecionado, Integer.parseInt((inQuantidade.getText().toString())));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
