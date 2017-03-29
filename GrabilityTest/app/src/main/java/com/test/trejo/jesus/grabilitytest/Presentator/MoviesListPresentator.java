package com.test.trejo.jesus.grabilitytest.Presentator;

import android.content.Context;
import android.util.Log;

import com.test.trejo.jesus.grabilitytest.Helper.Rest.ApiClient;
import com.test.trejo.jesus.grabilitytest.Helper.Rest.MyApiEndpointInterface;
import com.test.trejo.jesus.grabilitytest.Model.DAO.DAOMovie;
import com.test.trejo.jesus.grabilitytest.Model.DAO.IDAOMovie;
import com.test.trejo.jesus.grabilitytest.Model.Movie;
import com.test.trejo.jesus.grabilitytest.Model.MoviesResponse;
import com.test.trejo.jesus.grabilitytest.Presentator.IPresentator.IMoviesListPresentator;
import com.test.trejo.jesus.grabilitytest.View.IView.IMoviesListFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by jesus on 26/03/17.
 */

public class MoviesListPresentator extends Presentator implements IMoviesListPresentator {

    private IMoviesListFragment mFragment;
    private IDAOMovie daoMovie;
    private Context mContext;

    /**
     * Constructor de la clase MoviesListPresentator
     * @param view Recibe la instancia de la vista que invoco al presentador
     * @param context Recibe el contexto de la Aplicacion
     */
    public MoviesListPresentator(IMoviesListFragment view, Context context){
        this.mFragment = view;
        this.mContext = context;
    }


    /**
     * Metodo que permite realizar la llamada al Servicio web que
     * entrega la lista de peliculas Populares.
     * @param page Pagina que se desea obtener
     */
    @Override
    public void getPopularMovies(int page) {

        MyApiEndpointInterface apiService =
                ApiClient.getClient().create(MyApiEndpointInterface.class);

        Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY,page);
        call.enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                daoMovie = new DAOMovie();
                mFragment.manageRecyclerView(movies);

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                daoMovie = new DAOMovie();
                List<Movie> toView = daoMovie.FetchMovieByType(mContext,"popular");
                mFragment.manageRecyclerViewWithOutInternet(toView);

            }
        });



    }

    /**
     * Metodo que permite realizar la llamada al Servicio web que
     * entrega la lista de peliculas Top Rated.
     * @param page Pagina que se desea obtener
     */
    @Override
    public void getTopRatedMovies(int page) {
        MyApiEndpointInterface apiService =
                ApiClient.getClient().create(MyApiEndpointInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY, page);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                daoMovie = new DAOMovie();

                mFragment.manageRecyclerView(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                daoMovie = new DAOMovie();
                List<Movie> toView = daoMovie.FetchMovieByType(mContext,"top_rated");
                mFragment.manageRecyclerViewWithOutInternet(toView);            }
        });
    }

    /**
     * Metodo que permite realizar la llamada al Servicio web que
     * entrega la lista de peliculas Upcoming.
     * @param page Pagina que se desea obtener
     */
    @Override
    public void getUpcomingMovies(int page) {
        MyApiEndpointInterface apiService =
                ApiClient.getClient().create(MyApiEndpointInterface.class);
        Call<MoviesResponse> call = apiService.getUpcomingMovies(API_KEY, page);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                mFragment.manageRecyclerView(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                daoMovie = new DAOMovie();
                List<Movie> toView = daoMovie.FetchMovieByType(mContext,"upcoming");
                mFragment.manageRecyclerViewWithOutInternet(toView);
            }
        });
    }

    /**
     * Metodo que permite Enviar una lista de peliculas al DAO y alli sean
     * Almacenadas en BD
     * @param mList Lista que contiene las peliculas que se desean guardar
     * @param type Tipo de peliculas que van en la lista
     */
    @Override
    public void sendMoviesToDao(List<Movie> mList, String type) {
        daoMovie = new DAOMovie();
        daoMovie.AddMovies(mContext,mList,type);
    }

}
