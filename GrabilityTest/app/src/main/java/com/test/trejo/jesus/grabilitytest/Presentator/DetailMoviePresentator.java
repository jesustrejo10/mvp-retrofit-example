package com.test.trejo.jesus.grabilitytest.Presentator;

import android.content.Context;

import com.test.trejo.jesus.grabilitytest.Helper.Rest.ApiClient;
import com.test.trejo.jesus.grabilitytest.Helper.Rest.MyApiEndpointInterface;
import com.test.trejo.jesus.grabilitytest.Model.DAO.DAOMovie;
import com.test.trejo.jesus.grabilitytest.Model.DAO.IDAOMovie;
import com.test.trejo.jesus.grabilitytest.Model.Movie;
import com.test.trejo.jesus.grabilitytest.Presentator.IPresentator.IDetailMoviePresentator;
import com.test.trejo.jesus.grabilitytest.View.DetailMovieFragment;
import com.test.trejo.jesus.grabilitytest.View.IView.IDetailMovieFragment;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by jesus on 28/03/17.
 */

public class DetailMoviePresentator extends Presentator implements IDetailMoviePresentator {


    private IDAOMovie daoMovie;
    private Context mContext;
    private IDetailMovieFragment mFragment;

    /**
     * Constructor de la clase DetailMoviePresentator
     * @param context Recibe como entrada el Contexto de la aplicacion
     * @param fragment Recibe como entrada la instancia de la vista que lo invoco
     */
    public DetailMoviePresentator(Context context,DetailMovieFragment fragment){

        this.mFragment = fragment;
        this.mContext = context;

    }


    /**
     * Metodo que permite obtener una pelicula a detalle
     * @param id recibe como parametro el Id externo de la pelicula
     */
    @Override
    public void getDetailMovie(final int id) {
        MyApiEndpointInterface apiService =
                ApiClient.getClient().create(MyApiEndpointInterface.class);

        Call<Movie> call = apiService.getMovieDetails(id,API_KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                int statusCode = response.code();
                try{
                    Movie movie = response.body();
                    System.out.println("ola");
                    sendValuesToView(movie);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                daoMovie  = new DAOMovie();
                Movie m = daoMovie.FetchMovieById(mContext,Integer.toString(id));
                System.out.println("olamunod");
                sendValuesToView(m);

            }


        });

    }


    /**
     * Metodo que permite enviar a la vista que invoco al presentador
     * los valores a detalle que seran mostrados, Titulo, Descripcion e imagen
     * @param movie Recibe como entrada un objeto de tipo Movie
     */
    private void sendValuesToView(Movie movie){

        mFragment.setMovieName(movie.getOriginalTitle());
        mFragment.setMovieDescription(movie.getOverview());
        String base = "http://image.tmdb.org/t/p/w185"+movie.getBackdropPath();
        mFragment.setMovieImage(base);
    }
}
