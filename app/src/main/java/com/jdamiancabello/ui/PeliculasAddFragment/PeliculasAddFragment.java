package com.jdamiancabello.ui.PeliculasAddFragment;

/**
 * Clase que carga la vista para la insercción de películas contiene un listener para hacer callback y volver a
 * la lista en caso de que el insert sea correcto.
 *
 * Tambien muestra los errores a la hora de insertar tales como fecha o titulo vacío así como título duplicado
 *
 * Implementa la interfaz PeliculasAddContract.View
 *
 * @autor Damian Cabello
 */

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jdamiancabello.examen.R;
import com.jdamiancabello.examen.data.model.Pelicula;


public class PeliculasAddFragment extends Fragment implements PeliculasAddContract.View{
    private PeliculasAddContract.Presenter presenter;
    public static final String TAG = "PeliculasAddFragment";
    private OnFragmentInteractionListener mListener;

    private FloatingActionButton floatingActionButton;
    private EditText titulo, fecha;
    private Spinner spinner;

    public static PeliculasAddFragment newInstance() {
        PeliculasAddFragment fragment = new PeliculasAddFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_peliculas_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.spinner);
        titulo = view.findViewById(R.id.edTitulo);
        fecha = view.findViewById(R.id.edFecha);

        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.add(new Pelicula(titulo.getText().toString(),fecha.getText().toString(),spinner.getSelectedItem().toString()));
            }
        });
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
    public void showEmptyTitle() {
        showSnackBar(getString(R.string.errorEmptyTitle));
    }

    @Override
    public void showEmptyDate() {
        showSnackBar(getString(R.string.errorEmptyDate));
    }

    @Override
    public void showDuplicatedTitle() {
        showSnackBar(getString(R.string.errorDuplicatedFilm));
    }

    private void showSnackBar(String msg){
        Snackbar.make(getView(),msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(PeliculasAddContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSucces() {
        mListener.onSaved();
    }

    @Override
    public void onGenericError(String errorMessage) {

    }


    public interface OnFragmentInteractionListener {
        void onSaved();
    }
}
