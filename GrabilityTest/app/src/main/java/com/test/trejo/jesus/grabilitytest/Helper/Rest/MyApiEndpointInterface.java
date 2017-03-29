package com.test.trejo.jesus.grabilitytest.Helper.Rest;

import com.test.trejo.jesus.grabilitytest.Model.Movie;
import com.test.trejo.jesus.grabilitytest.Model.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jesus on 26/03/17.
 */

public interface MyApiEndpointInterface {


    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int id, @Query("api_key") String apiKey);

}
