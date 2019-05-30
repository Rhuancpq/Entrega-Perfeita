package com.oofga.ep.entregaperfeita;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.oofga.ep.veiculos.Frota;

public class MainActivity extends AppCompatActivity implements SettingsFragment.SettingsListener, ActionFragment.ActionListener {
    Frota frota = new Frota();
    SettingsFragment settingsFragment;
    ActionFragment actionFragment;
    Button btnAdicionar, btnRemover, btnDesocupar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingsFragment = new SettingsFragment();
        actionFragment = new ActionFragment();
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnDesocupar = findViewById(R.id.btnDesocupar);
        btnRemover = findViewById(R.id.btnRemover);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnAdicionarListener(v);
            }
        });

        btnDesocupar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnRemoverListener(v);
            }
        });

        btnRemover.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnDesocuparListener(v);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop(){
        super.onStop();
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
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragmentContainer,settingsFragment);
            ft.addToBackStack(null);
            ft.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onButtonClick(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(settingsFragment);
        ft.commit();
    }

    @Override
    public void onActionButtonClick(String tipo, int Quantidade){
        //Stuff here
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(actionFragment);
        ft.commit();
    }
    private void attachActionFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragmentContainer,actionFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    private void btnAdicionarListener(View v){
        actionFragment.setTipoAcao("Adicionar");
        attachActionFragment();
    }
    private void btnDesocuparListener(View v){
        actionFragment.setTipoAcao("Desocupar");
        attachActionFragment();
    }
    private void btnRemoverListener(View v){
        actionFragment.setTipoAcao("Remover");
        attachActionFragment();
    }
}
