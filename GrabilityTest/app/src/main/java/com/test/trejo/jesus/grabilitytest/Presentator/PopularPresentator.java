package com.test.trejo.jesus.grabilitytest.Presentator;

import android.util.Log;

import com.test.trejo.jesus.grabilitytest.Helper.Rest.ApiClient;
import com.test.trejo.jesus.grabilitytest.Helper.Rest.MyApiEndpointInterface;
import com.test.trejo.jesus.grabilitytest.Model.IModel.IMovie;
import com.test.trejo.jesus.grabilitytest.Model.Movie;
import com.test.trejo.jesus.grabilitytest.Model.MoviesResponse;
import com.test.trejo.jesus.grabilitytest.Presentator.IPresentator.IPopularPresentator;
import com.test.trejo.jesus.grabilitytest.R;
import com.test.trejo.jesus.grabilitytest.View.IView.IPopularFragment;
import com.test.trejo.jesus.grabilitytest.View.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by jesus on 26/03/17.
 */

public class PopularPresentator extends Presentator implements IPopularPresentator{
    private String API_KEY = "157dfc341b3c40a79aca5616d49a0429";
    private IMovie movie;
    private IPopularFragment mFragment;

    public PopularPresentator(IPopularFragment v){

        this.mFragment = v;
    }


    @Override
    public String getName() {
        mFragment.showToast("jojojo");
        return "hey como estas";
    }

    @Override
    public void getPopularMovies() {


        MyApiEndpointInterface apiService =
                ApiClient.getClient().create(MyApiEndpointInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                mFragment.manageRecyclerView(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });



    }

    //https://api.themoviedb.org/3/movie/550?api_key=157dfc341b3c40a79aca5616d49a0429
}
