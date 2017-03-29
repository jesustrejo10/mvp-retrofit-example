package com.test.trejo.jesus.grabilitytest.Presentator.IPresentator;

import com.test.trejo.jesus.grabilitytest.Model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus on 26/03/17.
 */

public interface IMoviesListPresentator {

    void getPopularMovies(int page);

    void getTopRatedMovies(int page);

    void getUpcomingMovies(int page);

    void sendMoviesToDao(List<Movie> mList, String type);

}
