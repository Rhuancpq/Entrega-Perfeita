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

import com.oofga.ep.utilidade.Frete;
import com.oofga.ep.utilidade.Registro;
import com.oofga.ep.veiculos.Frota;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements SettingsFragment.SettingsListener, ActionFragment.ActionListener,
        RegisterFragment.RegisterListener, SelectionFragment.SelectionListener,
        RecordFragment.RecordListener, RemoveFragment.RemoveListener {
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
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                    toast.show();
                } else fbtnEntregaListener(v);
            }
        });
        fbtnListaFretes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fretes.isEmpty()) {
                    String text = "A Lista de Fretes está vazia, impossível listar fretes";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                    toast.show();
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
    protected void onResume() {
        super.onResume();
        carregarDados();
        atualizarInformacoes();
    }

    @Override
    protected void onPause() {
        super.onPause();
        persistirDados();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        persistirDados();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
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
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.fragmentContainer1, settingsFragment);
                ft.addToBackStack(null);
                persistirDados();
                ft.commit();
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
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(settingsFragment);
        fm.popBackStack();
        ft.commit();
    }

    private boolean verificarVeiculos(String tipo, int quantidade) {
        switch (tipo) {
            case "Carreta":
                return frota.getQntCarretasDisp() >= quantidade;
            case "Carro":
                return frota.getQntCarrosDisp() >= quantidade;
            case "Moto":
                return frota.getQntMotosDisp() >= quantidade;
            case "Van":
                return frota.getQntVansDisp() >= quantidade;
            default:
                return false;

        }
    }

    @Override
    public void onActionButtonClick(String tipo, int quantidade, String tipoAcao) {
        switch (tipoAcao) {
            case "Adicionar":
                frota.adicionarVeiculos(tipo, quantidade);
                break;
            case "Remover":
                if (verificarVeiculos(tipo, quantidade)) {
                    frota.removerVeiculos(tipo, quantidade);
                } else {
                    String text = "Quantidade disponivel é menor do que a quantidade que deseja remover";
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
        }
        //Removendo o Fragmento da exibição
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(atualActionFragment);
        fm.popBackStack();
        persistirDados();
        atualizarInformacoes();
        ft.commit();
    }

    private void attachFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentContainer1, f);
        ft.addToBackStack(null);
        ft.commit();
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
        freteAtual.setCusto(registro.getCustoTotal());
        freteAtual.setTempo(registro.getTempo());
        freteAtual.setVeiculo(registro.getTipoVeiculo());
        freteAtual.setId(uniqueId);
        uniqueId++;
        fretes.add(freteAtual);
        custoTotal += registro.getCustoTotal();
        frota.ocuparVeiculo(registro.getTipoVeiculo());
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(selectionFragment);
        fm.popBackStack();
        ft.remove(registerFragment);
        fm.popBackStack();
        ft.commit();
        atualizarInformacoes();
    }


    @Override
    public void onRegisterButtonClick(String name, double distancia, double carga, double tempoMax) {
        freteAtual = new Frete();
        freteAtual.setNome(name);
        freteAtual.setCarga(carga);
        freteAtual.setDistancia(distancia);
        selectionFragment = new SelectionFragment();
        selectionFragment.setVeiculoRapido(frota.veiculoMaisRapido(carga,
                distancia, tempoMax, margemLucro));
        selectionFragment.setVeiculoBarato(frota.veiculoMenorCusto(carga,
                distancia, tempoMax, margemLucro));
        selectionFragment.setVeiculoBeneficio(frota.veiculoMelhorCustoBeneficio(carga,
                distancia, tempoMax, margemLucro));
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer1, selectionFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void onFbtnRemoverClick() {
        removeFragment = new RemoveFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer1, removeFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBtnRemoverFreteClick(int Id) {
        boolean invalid = true;
        Iterator itr = fretes.iterator();
        while (itr.hasNext()) {
            Frete x = (Frete) itr.next();
            if (x.getId() == Id) {
                itr.remove();
                invalid = false;
                break;
            }
        }
        if (invalid) {
            String text = "Id Inválido";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();
        } else {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(removeFragment);
            fm.popBackStack();
            ft.remove(recordFragment);
            fm.popBackStack();
            ft.commit();
            persistirDados();
            atualizarInformacoes();
        }
    }

    private void fbtnEntregaListener(View v) {
        registerFragment = new RegisterFragment();
        attachFragment(registerFragment);
    }

    private void fbtnListaFretesListener(View v) {
        toolbar.setVisibility(Toolbar.GONE);
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
        int qntCarretas = 0, qntCarros = 0, qntMotos = 0, qntVans = 0;
        if (sharedPreferences.contains("margemLucro")) {
            String temp = sharedPreferences.getString("margemLucro", "0.0");
            if (temp != null) {
                margemLucro = Double.parseDouble((temp));
            }
        }

        qntCarretas = sharedPreferences.getInt("qntCarretas", 0);

        qntCarros = sharedPreferences.getInt("qntCarros", 0);

        qntMotos = sharedPreferences.getInt("qntMotos", 0);

        qntVans = sharedPreferences.getInt("qntVans", 0);

        if (qntCarretas != 0) {
            frota.adicionarVeiculos("Carreta", qntCarretas);
        }

        if (qntCarros != 0) {
            frota.adicionarVeiculos("Carros", qntCarros);
        }

        if (qntMotos != 0) {
            frota.adicionarVeiculos("Carreta", qntMotos);
        }

        if (qntVans != 0) {
            frota.adicionarVeiculos("Carreta", qntVans);
        }
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
