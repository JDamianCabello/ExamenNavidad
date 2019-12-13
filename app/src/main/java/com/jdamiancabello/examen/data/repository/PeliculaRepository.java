package com.jdamiancabello.examen.data.repository;

/**
 * Clase singletón que contiene una lista con nuestros datos.
 *
 * contiene los métodos de add, delete así como su getter (getPeliculaList())
 *
 * @author Damián Cabello
 */

import com.jdamiancabello.examen.data.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class PeliculaRepository {
    private List<Pelicula> peliculaList;
    public static PeliculaRepository getInstance;

    static {
        getInstance = new PeliculaRepository();
    }

    public PeliculaRepository() {
        this.peliculaList = new ArrayList<>();
        iniciaRepo();
    }

    private void iniciaRepo() {
        this.peliculaList.add(new Pelicula("Los vengadores","2019","Aventura"));
        this.peliculaList.add(new Pelicula("Aliens","1986","Documental"));
        this.peliculaList.add(new Pelicula("Chicken run","2000","Comedia"));
    }

    public List<Pelicula> getPeliculaList(){
        return this.peliculaList;
    }

    public boolean removePelicula(Pelicula pelicula){
        return peliculaList.remove(pelicula);
    }

    public boolean addPelicula(Pelicula pelicula){
        return peliculaList.add(pelicula);
    }
}
