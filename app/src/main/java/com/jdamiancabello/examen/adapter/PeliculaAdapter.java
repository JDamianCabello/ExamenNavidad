package com.jdamiancabello.examen.adapter;

/**
 * Clase que indica a la lista como deben ser visualizados los datos
 *
 * contiene una interfaz para indicar los listeners de cada ViewHolder
 *
 * @author Damián Cabello
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jdamiancabello.examen.R;
import com.jdamiancabello.examen.data.model.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.ViewHolder> {
    private List<Pelicula> peliculas;
    private AdapterListener listener;

    public PeliculaAdapter(AdapterListener listener) {
        this.listener = listener;
        peliculas = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foto.setImageResource(peliculas.get(position).getImagen());
        holder.nombre.setText(peliculas.get(position).getTitulo());
        holder.genero.setText(peliculas.get(position).getGenero());
        holder.fecha.setText(peliculas.get(position).getFecha());

        holder.bind(peliculas.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }

    public void addAll(List<Pelicula> nuevasPelis){
        this.peliculas.addAll(nuevasPelis);
    }

    public void clear(){
        this.peliculas.clear();
    }

    public void remove(Pelicula pelicula) {
        this.peliculas.remove(pelicula);
    }

    public void add(Pelicula pelicula) {
        this.peliculas.add(pelicula);
    }

    public void sortTitle() {
        this.peliculas.sort(new Pelicula.SortTittle());
    }

    public void sortYear() {
        this.peliculas.sort(new Pelicula.SortAnnio());
    }


    public interface AdapterListener{
        void onDelete(Pelicula pelicula);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre,fecha,genero;
        ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.item_PeliculaName);
            fecha = itemView.findViewById(R.id.item_PeliculaAnnio);
            genero = itemView.findViewById(R.id.item_PeliculaGenero);
            foto = itemView.findViewById(R.id.item_PeliculaImagen);
        }

        public void bind(final Pelicula pelicula, final AdapterListener adapterListener){
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    adapterListener.onDelete(pelicula);
                    return true;
                }
            });
        }
    }
}
