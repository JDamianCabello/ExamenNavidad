package com.jdamiancabello.ui.listar;

/**
 * Clase que carga la vista para la lista de películas contiene un listener para hacer callback cargar
 * otro fragment en caso de pulsar en el botón flotante
 *
 * Tambien premite borrar, deshacer el borrado así como listar por año y por título
 *
 * por defecto muestra la lista ordenada por título
 *
 * Implementa la interfaz PeliculasListContract.View
 * @autor Damian Cabello
 */

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jdamiancabello.examen.R;
import com.jdamiancabello.examen.adapter.PeliculaAdapter;
import com.jdamiancabello.examen.data.model.Pelicula;

import java.util.List;


public class PeliculasListFragment extends Fragment implements PeliculasListContract.View{
    private PeliculasListContract.Presenter presenter;
    private PeliculaAdapter adapter;
    public static final String TAG = "PeliculasListFragment";
    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;


    public static PeliculasListFragment newInstance() {
        PeliculasListFragment fragment = new PeliculasListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.load();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sortDate:
                adapter.sortYear();
                break;
            case R.id.sortTitle:
                adapter.sortTitle();
                break;
        }
        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_peliculas_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton = view.findViewById(R.id.fabAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAdd();
            }
        });

        adapter = new PeliculaAdapter(new PeliculaAdapter.AdapterListener() {
            @Override
            public void onDelete(final Pelicula pelicula) {
                new AlertDialog.Builder(getContext())
                        .setMessage(getString(R.string.listDelete) + " " + pelicula.getTitulo()+"?")
                        .setNegativeButton(getString(R.string.listDeleteCancelButton), null)
                        .setPositiveButton(R.string.listDeleteOkButton, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                presenter.deletePelicula(pelicula);
                            }
                        }).show();
            }
        });

        recyclerView = view.findViewById(R.id.rvList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void refresh(List<Pelicula> peliculaList) {
        adapter.clear();
        adapter.addAll(peliculaList);
        adapter.sortTitle();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStartUndo(final Pelicula pelicula) {
        adapter.remove(pelicula);
        adapter.notifyDataSetChanged();
        Snackbar.make(getView(),getString(R.string.listUndoMessage) + " "+pelicula.getTitulo() + "?",Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.listUndoOk), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.onOkUndo(pelicula);
                    }
                }).show();
    }

    @Override
    public void onSuccesUndo(Pelicula pelicula) {
        adapter.add(pelicula);
        adapter.sortTitle();
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(),getString(R.string.listRestoredText)+" " + pelicula.getTitulo(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PeliculasListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSucces() {

    }

    @Override
    public void onGenericError(String errorMessage) {
        Toast.makeText(getContext(),errorMessage,Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        void onAdd();
    }
}
