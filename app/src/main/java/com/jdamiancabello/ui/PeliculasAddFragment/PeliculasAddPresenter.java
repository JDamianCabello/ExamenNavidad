package com.jdamiancabello.ui.PeliculasAddFragment;

/**
 * Clase que gestiona la insercción de películas contiene métodos para validar los datos y accede al repo según necesite
 *
 * Implementa la interfaz PeliculasAddContract.Presenter
 *
 * @autor Damian Cabello
 */

import com.jdamiancabello.examen.data.model.Pelicula;
import com.jdamiancabello.examen.data.repository.PeliculaRepository;

public class PeliculasAddPresenter implements PeliculasAddContract.Presenter {
    private PeliculasAddContract.View view;

    public PeliculasAddPresenter(PeliculasAddContract.View view) {
        this.view = view;
    }

    @Override
    public void add(Pelicula pelicula) {
        if(validateAll(pelicula)) {
            if (PeliculaRepository.getInstance.addPelicula(pelicula))
                view.onSucces();
        }
    }

    private boolean validateAll(Pelicula pelicula){
        if(emptyTitle(pelicula.getTitulo()) || emptyDate(pelicula.getFecha())||duplicatedFilm(pelicula.getTitulo()))
            return false;
        else
            return true;
    }



    private boolean emptyDate(String date){
        if(date.isEmpty()||date.length()<4) {
            view.showEmptyDate();
            return true;
        }
        return false;
    }

    private boolean emptyTitle(String title){
        if(title.isEmpty()){
            view.showEmptyTitle();
            return true;
        }
        return false;

    }

    private boolean duplicatedFilm(String title){
        for (Pelicula p : PeliculaRepository.getInstance.getPeliculaList())
            if(p.getTitulo().equals(title)) {
                view.showDuplicatedTitle();
                return true;
            }
        return false;
    }
}
