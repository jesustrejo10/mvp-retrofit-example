package com.test.trejo.jesus.grabilitytest.View.IView;

import com.test.trejo.jesus.grabilitytest.Model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus on 26/03/17.
 */

public interface IMoviesListFragment extends IView {

    void showToast(String text);

    void manageRecyclerView(List<Movie> movieList);

    void manageRecyclerViewWithOutInternet(List<Movie> movieList);

    String getType();

    int getPage();

    void incrementPage();

    void stopLoading();
}
