package com.jdamiancabello.ui.listar;

import com.jdamiancabello.examen.data.model.Pelicula;
import com.jdamiancabello.examen.data.repository.PeliculaRepository;

/**
 * Clase que controla los datos de la lista (load()), así como insertar (en el caso de undo) y borrar.
 *
 *
 * Implementa la interfaz PeliculasListContract.Presenter
 * @autor Damian Cabello
 */

public class PeliculasListPresenter implements PeliculasListContract.Presenter{
    private PeliculasListContract.View view;

    public PeliculasListPresenter(PeliculasListContract.View view) {
        this.view = view;
    }

    @Override
    public void deletePelicula(Pelicula pelicula) {
        if(PeliculaRepository.getInstance.removePelicula(pelicula))
            view.onStartUndo(pelicula);
        else
            view.onGenericError("Error");
    }

    @Override
    public void onOkUndo(Pelicula pelicula) {
        if(PeliculaRepository.getInstance.addPelicula(pelicula))
            view.onSuccesUndo(pelicula);
        else
            view.onGenericError("Error");
    }

    @Override
    public void load() {
        view.refresh(PeliculaRepository.getInstance.getPeliculaList());
    }
}
