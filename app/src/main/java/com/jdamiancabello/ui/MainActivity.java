package com.jdamiancabello.ui;

/**
 * Clase principal donde se van cargando los fragments según el usuario use la app
 * @autor Damian Cabello
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jdamiancabello.examen.R;
import com.jdamiancabello.ui.PeliculasAddFragment.PeliculasAddFragment;
import com.jdamiancabello.ui.PeliculasAddFragment.PeliculasAddPresenter;
import com.jdamiancabello.ui.listar.PeliculasListFragment;
import com.jdamiancabello.ui.listar.PeliculasListPresenter;

public class MainActivity extends AppCompatActivity implements
        PeliculasListFragment.OnFragmentInteractionListener,
        PeliculasAddFragment.OnFragmentInteractionListener{

    private PeliculasListFragment peliculasListFragment;
    private PeliculasListPresenter peliculasListPresenter;

    private PeliculasAddFragment peliculasAddFragment;
    private PeliculasAddPresenter peliculasAddPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peliculasListFragment = (PeliculasListFragment) getSupportFragmentManager().findFragmentByTag(PeliculasListFragment.TAG);

        if(peliculasListFragment == null){
            peliculasListFragment = PeliculasListFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content,peliculasListFragment,PeliculasListFragment.TAG)
                    .commit();
        }

        peliculasListPresenter = new PeliculasListPresenter(peliculasListFragment);
        peliculasListFragment.setPresenter(peliculasListPresenter);

    }

    @Override
    public void onAdd() {
        peliculasAddFragment = (PeliculasAddFragment) getSupportFragmentManager().findFragmentByTag(PeliculasAddFragment.TAG);

        if(peliculasAddFragment == null){
            peliculasAddFragment = PeliculasAddFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content,peliculasAddFragment,PeliculasAddFragment.TAG)
                    .addToBackStack(null)
                    .commit();
        }

        peliculasAddPresenter = new PeliculasAddPresenter(peliculasAddFragment);
        peliculasAddFragment.setPresenter(peliculasAddPresenter);
    }

    @Override
    public void onSaved() {
        onBackPressed();
    }
}
