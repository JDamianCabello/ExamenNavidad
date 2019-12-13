package com.jdamiancabello.ui.listar;

/**
 * Clase que contiene las interfaces necesarias para realizar el MVP de listar
 *
 * View hereda de BaseView<T>
 * @autor Damian Cabello
 */

import com.jdamiancabello.base.BaseView;
import com.jdamiancabello.examen.data.model.Pelicula;

import java.util.List;

public class PeliculasListContract {
    interface View extends BaseView<Presenter> {
        void refresh(List<Pelicula> peliculaList);
        void onStartUndo(Pelicula pelicula);
        void onSuccesUndo(Pelicula pelicula);
    }

    interface Presenter{
        void deletePelicula(Pelicula pelicula);
        void onOkUndo(Pelicula pelicula);
        void load();
    }
}
