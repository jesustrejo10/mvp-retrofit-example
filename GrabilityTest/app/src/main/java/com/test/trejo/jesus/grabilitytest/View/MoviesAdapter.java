package com.test.trejo.jesus.grabilitytest.View;

/**
 * Created by jesus on 27/03/17.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.trejo.jesus.grabilitytest.Helper.OnFragmentSwap;
import com.test.trejo.jesus.grabilitytest.Model.Movie;
import com.test.trejo.jesus.grabilitytest.R;

import java.util.List;

import static com.test.trejo.jesus.grabilitytest.R.layout.list_item_movie;

/**
 * Adapter para mostrar la lista de peliculas
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    private OnFragmentSwap mCallBack;

    public List<Movie> getMovies() {
        return movies;
    }


    /**
     * Clase MovieViewHolder, aca se declaran los elmentos que seran mostrados
     * en cada fila
     */
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        /**
         * Se asignan a los campos de la vista sus valores reales
         * @param v recibe la Vista
         */

        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);


        }
    }

    /**
     * Constructor de la clase MoviesAdapter
     * @param movies Recibe la lista de peliculas que seran mostradas
     * @param rowLayout
     * @param context
     * @param mCallBack
     */
    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context, OnFragmentSwap mCallBack) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mCallBack = mCallBack;
    }


    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(list_item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    /**
     * Metodo Que se ejecuta Cada vez que es asignada una fila
     * Se utiliza para realizar el Listener del click en la fila
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        holder.moviesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id  = movies.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("movieId",id);
                mCallBack.onSwap("Detail",bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}