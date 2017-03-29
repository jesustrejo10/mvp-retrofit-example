package com.test.trejo.jesus.grabilitytest.Presentator;

import android.content.Context;
import android.util.Log;

import com.test.trejo.jesus.grabilitytest.Helper.Rest.ApiClient;
import com.test.trejo.jesus.grabilitytest.Helper.Rest.MyApiEndpointInterface;
import com.test.trejo.jesus.grabilitytest.Model.DAO.DAOMovie;
import com.test.trejo.jesus.grabilitytest.Model.DAO.IDAOMovie;
import com.test.trejo.jesus.grabilitytest.Model.Movie;
import com.test.trejo.jesus.grabilitytest.Model.MoviesResponse;
import com.test.trejo.jesus.grabilitytest.Model.Util;
import com.test.trejo.jesus.grabilitytest.Presentator.IPresentator.IMoviesListPresentator;
import com.test.trejo.jesus.grabilitytest.View.IView.IMoviesListFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.test.trejo.jesus.grabilitytest.Model.Util.CONECTION_ERROR;

/**
 * Created by jesus on 26/03/17.
 */

public class MoviesListPresentator extends Presentator implements IMoviesListPresentator {

    private IMoviesListFragment mFragment;
    private IDAOMovie daoMovie;
    private Context mContext;

    public MoviesListPresentator(IMoviesListFragment v, Context c){
        this.mFragment = v;
        this.mContext = c;
    }


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

    @Override
    public void sendMoviesToDao(List<Movie> mList, String type) {
        daoMovie = new DAOMovie();
        daoMovie.AddMovies(mContext,mList,type);
    }

}
