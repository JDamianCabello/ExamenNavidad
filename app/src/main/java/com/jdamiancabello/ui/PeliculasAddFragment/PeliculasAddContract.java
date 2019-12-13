package com.jdamiancabello.ui.PeliculasAddFragment;

import com.jdamiancabello.base.BaseView;
import com.jdamiancabello.examen.data.model.Pelicula;
/**
 * Clase que contiene las interfaces necesarias para realizar el MVP de añadir
 *
 * View hereda de BaseView<T>
 * @autor Damian Cabello
 */


public class PeliculasAddContract {
    interface View extends BaseView<Presenter> {
        void showEmptyTitle();
        void showEmptyDate();
        void showDuplicatedTitle();
    }

    interface Presenter{
        void add(Pelicula pelicula);
    }
}
