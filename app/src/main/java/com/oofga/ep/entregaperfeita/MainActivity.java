package com.oofga.ep.entregaperfeita;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oofga.ep.Controller.FreteController;
import com.oofga.ep.Controller.FrotaController;
import com.oofga.ep.Model.Fretes.Frete;
import com.oofga.ep.Model.Fretes.Registro;
import com.oofga.ep.Model.Veiculos.Frota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements FragmentListener {
    Frota frota;
    Frete freteAtual;
    private ArrayList<Frete> fretes;
    boolean settingsOpened;
    int uniqueId = 1;
    SettingsFragment settingsFragment;
    ActionFragment atualActionFragment;
    RecordFragment recordFragment;
    RemoveFragment removeFragment;
    RegisterFragment registerFragment;
    SelectionFragment selectionFragment;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    Button btnAdicionar, btnRemover, btnDesocupar;
    FloatingActionButton fbtnEntrega, fbtnListaFretes;
    TextView carretaDisp, carroDisp, motoDisp, vanDisp,
            carretaInd, carroInd, motoInd, vanInd,
            carreta, carro, moto, van, Dispo, Ocupado;
    Double margemLucro, custoTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frota = new Frota();
        fretes = new ArrayList<>();
        settingsFragment = new SettingsFragment();
        setContentView(R.layout.activity_main);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnRemover = findViewById(R.id.btnRemover);
        carretaDisp = findViewById(R.id.carretaDisp);
        carretaInd = findViewById(R.id.carretaInd);
        carroDisp = findViewById(R.id.carroDisp);
        carroInd = findViewById(R.id.carroInd);
        motoDisp = findViewById(R.id.motoDisp);
        motoInd = findViewById(R.id.motoInd);
        vanDisp = findViewById(R.id.vanDisp);
        vanInd = findViewById(R.id.vanInd);
        carreta = findViewById(R.id.carreta);
        carro = findViewById(R.id.carro);
        moto = findViewById(R.id.moto);
        van = findViewById(R.id.van);
        Dispo = findViewById(R.id.Dispo);
        Ocupado = findViewById(R.id.Ocupado);
        fbtnEntrega = findViewById(R.id.entrega);
        fbtnListaFretes = findViewById(R.id.listaFretes);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        settingsOpened = false;
        custoTotal = 0d;
        margemLucro = 0d;
        sharedPreferences = getSharedPreferences(getString(R.string.pref_key),
                Context.MODE_PRIVATE);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnAdicionarListener(v);
            }
        });
        btnRemover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnRemoverListener(v);
            }
        });
        fbtnEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frota.getLista().isEmpty()) {
                    String text = "A Frota está vazia, impossível realizar encomenda";
                    showToast(text);
                } else fbtnEntregaListener(v);
            }
        });
        fbtnListaFretes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fretes.isEmpty()) {
                    String text = "A Lista de Fretes está vazia, impossível listar fretes";
                    showToast(text);
                } else fbtnListaFretesListener(v);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarDados();
        atualizarInformacoes();
    }

    @Override
    protected void onStop() {
        super.onStop();
        persistirDados();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if (!settingsOpened) {
                settingsOpened = true;
                attachFragment(settingsFragment);
                settingsFragment.setText(margemLucro*100);
                persistirDados();
                return true;
            } else {
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        settingsOpened = false;
        toolbar.setVisibility(Toolbar.VISIBLE);

    }


    @Override
    public void onSettingsButtonClick(double margem) {
        settingsOpened = false;
        margemLucro = margem / 100;
        disattachFragment(settingsFragment);
    }

    @Override
    public void onActionButtonClick(String tipo, int quantidade, String tipoAcao) {
        if (!FrotaController.frotaAction(frota, tipo, quantidade, tipoAcao)) {
            String text = "Quantidade disponivel é menor do que a quantidade que deseja remover";
            showToast(text);
        }
        //Removendo o Fragmento da exibição
        disattachFragment(atualActionFragment);
        persistirDados();
        atualizarInformacoes();
    }

    private void attachFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentContainer1, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void disattachFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(f);
        fm.popBackStack();
        ft.commit();
    }

    private void disattachPairFragment(Fragment first, Fragment second) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(first);
        fm.popBackStack();
        ft.remove(second);
        fm.popBackStack();
        ft.commit();
    }

    private void replaceFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer1, f);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void showToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }

    private void btnAdicionarListener(View v) {
        atualActionFragment = new ActionFragment();
        atualActionFragment.setTipoAcao("Adicionar");
        attachFragment(atualActionFragment);
    }

    private void btnRemoverListener(View v) {
        atualActionFragment = new ActionFragment();
        atualActionFragment.setTipoAcao("Remover");
        attachFragment(atualActionFragment);
    }

    @Override
    public void onBtnVeiculoClick(Registro registro) {
        FreteController.finalizarFrete(freteAtual, registro, uniqueId);
        uniqueId++;
        fretes.add(freteAtual);
        custoTotal += registro.getCustoTotal();
        frota.ocuparVeiculo(registro.getTipoVeiculo());
        disattachPairFragment(selectionFragment,
                registerFragment);
        atualizarInformacoes();
    }


    @Override
    public void onRegisterButtonClick(String name, double distancia, double carga, double tempoMax) {
        freteAtual = FreteController.registrarFrete(name, carga, distancia);
        selectionFragment = new SelectionFragment();
        selectionFragment.setVeiculoRapido(frota.veiculoMaisRapido(carga,
                distancia, tempoMax, margemLucro));
        selectionFragment.setVeiculoBarato(frota.veiculoMenorCusto(carga,
                distancia, tempoMax, margemLucro));
        selectionFragment.setVeiculoBeneficio(frota.veiculoMelhorCustoBeneficio(carga,
                distancia, tempoMax, margemLucro));
        replaceFragment(selectionFragment);
    }

    public void onFbtnRemoverClick() {
        removeFragment = new RemoveFragment();
        replaceFragment(removeFragment);
    }

    @Override
    public void onBtnRemoverFreteClick(int Id) {
        if (FreteController.removerFrete(frota,fretes, Id)) {
            String text = "Id Inválido";
            showToast(text);
        } else {
            disattachPairFragment(removeFragment,
                    recordFragment);
            persistirDados();
            atualizarInformacoes();
        }
    }

    private void fbtnEntregaListener(View v) {
        registerFragment = new RegisterFragment();
        attachFragment(registerFragment);
    }

    private void fbtnListaFretesListener(View v) {
        recordFragment = new RecordFragment();
        recordFragment.presetValues(fretes, custoTotal);
        attachFragment(recordFragment);
    }

    private void persistirDados() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("margemLucro", margemLucro.toString());
        editor.putInt("qntCarretas", frota.getQntCarretas());
        editor.putInt("qntCarros", frota.getQntCarros());
        editor.putInt("qntMotos", frota.getQntMotos());
        editor.putInt("qntVans", frota.getQntVans());
        editor.apply();
    }

    private void carregarDados() {
        if (sharedPreferences.contains("margemLucro")) {
            String temp = sharedPreferences.getString("margemLucro", "0.0");
            if (temp != null) {
                margemLucro = Double.parseDouble((temp));
            }
        }
        frota = new Frota();
        FrotaController.carregarFrota(sharedPreferences, frota);
    }

    private void atualizarInformacoes() {
        if (frota.getLista().isEmpty()) {
            carretaDisp.setVisibility(EditText.INVISIBLE);
            carretaInd.setVisibility(EditText.INVISIBLE);
            carroDisp.setVisibility(EditText.INVISIBLE);
            carroInd.setVisibility(EditText.INVISIBLE);
            motoDisp.setVisibility(EditText.INVISIBLE);
            motoInd.setVisibility(EditText.INVISIBLE);
            vanDisp.setVisibility(EditText.INVISIBLE);
            vanInd.setVisibility(EditText.INVISIBLE);
            carreta.setVisibility(EditText.INVISIBLE);
            carro.setVisibility(EditText.INVISIBLE);
            moto.setVisibility(EditText.INVISIBLE);
            van.setVisibility(EditText.INVISIBLE);
            Dispo.setVisibility(EditText.INVISIBLE);
            Ocupado.setVisibility(EditText.INVISIBLE);
        } else {
            carretaDisp.setVisibility(EditText.VISIBLE);
            carretaInd.setVisibility(EditText.VISIBLE);
            carroDisp.setVisibility(EditText.VISIBLE);
            carroInd.setVisibility(EditText.VISIBLE);
            motoDisp.setVisibility(EditText.VISIBLE);
            motoInd.setVisibility(EditText.VISIBLE);
            vanDisp.setVisibility(EditText.VISIBLE);
            vanInd.setVisibility(EditText.VISIBLE);
            carreta.setVisibility(EditText.VISIBLE);
            carro.setVisibility(EditText.VISIBLE);
            moto.setVisibility(EditText.VISIBLE);
            van.setVisibility(EditText.VISIBLE);
            Dispo.setVisibility(EditText.VISIBLE);
            Ocupado.setVisibility(EditText.VISIBLE);
            carretaDisp.setText(String.valueOf(frota.getQntCarretasDisp()));
            carroDisp.setText(String.valueOf(frota.getQntCarrosDisp()));
            motoDisp.setText(String.valueOf(frota.getQntMotosDisp()));
            vanDisp.setText(String.valueOf(frota.getQntVansDisp()));
            carretaInd.setText(String.valueOf(frota.getCarretasOcupadas()));
            carroInd.setText(String.valueOf(frota.getCarrosOcupados()));
            motoInd.setText(String.valueOf(frota.getMotosOcupadas()));
            vanInd.setText(String.valueOf(frota.getVansOcupadas()));
        }
    }
}
