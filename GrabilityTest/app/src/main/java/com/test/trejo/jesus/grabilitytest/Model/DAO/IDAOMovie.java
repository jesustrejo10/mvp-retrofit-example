package com.test.trejo.jesus.grabilitytest.Model.DAO;

import android.content.Context;

import com.test.trejo.jesus.grabilitytest.Model.Movie;

import java.util.List;

/**
 * Created by jesus on 29/03/17.
 */

public interface IDAOMovie {


    List<Movie> FetchMovieByType(Context context,String type);

    void RemoveMovieByType(Context context,String type);

    void AddMovies(Context context,List<Movie> movies, String type);

    Movie FetchMovieById(Context context , String id);

}
