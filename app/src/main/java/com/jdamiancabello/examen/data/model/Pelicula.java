package com.jdamiancabello.examen.data.model;

/**
 * Clase modelo de nuestra aplicación.
 *
 * contiene los métodos getter y setter necesarios así como los sort para ordenar la lista segun criterios.
 *
 * @author Damián Cabello
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.jdamiancabello.examen.R;

import java.util.Comparator;

public class Pelicula implements Parcelable {
    private String titulo, fecha, genero;
    private int imagen;

    public Pelicula(String titulo, String fecha, String genero) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.genero = genero;
        this.imagen = selectImage(this.genero);
    }

    protected Pelicula(Parcel in) {
        titulo = in.readString();
        fecha = in.readString();
        genero = in.readString();
        imagen = in.readInt();
    }

    public static final Creator<Pelicula> CREATOR = new Creator<Pelicula>() {
        @Override
        public Pelicula createFromParcel(Parcel in) {
            return new Pelicula(in);
        }

        @Override
        public Pelicula[] newArray(int size) {
            return new Pelicula[size];
        }
    };

    private int selectImage(String genero) {
        switch (genero){
            case "Documental":
                return R.drawable.documental;
            case "Aventura":
                return R.drawable.adventure;
            case "Comedia":
                return R.drawable.comedy;
            case "Drama":
                return R.drawable.drama;
            case "Terror":
                return R.drawable.terror;
        }
        return -1;
    }

    public String getTitulo() {
        return titulo;
    }


    public String getFecha() {
        return fecha;
    }


    public String getGenero() {
        return genero;
    }


    public int getImagen() {
        return imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(fecha);
        parcel.writeString(genero);
        parcel.writeInt(imagen);
    }

    public static class SortTittle implements Comparator<Pelicula> {

        @Override
        public int compare(Pelicula pelicula, Pelicula t1) {
            return pelicula.getTitulo().compareToIgnoreCase(t1.getTitulo());
        }
    }

    public static class SortAnnio implements Comparator<Pelicula>{

        @Override
        public int compare(Pelicula pelicula, Pelicula t1) {
            return pelicula.getFecha().compareToIgnoreCase(t1.getFecha());
        }
    }
}

