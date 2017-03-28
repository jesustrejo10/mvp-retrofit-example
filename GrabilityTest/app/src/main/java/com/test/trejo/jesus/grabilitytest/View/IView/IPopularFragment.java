package com.test.trejo.jesus.grabilitytest.View.IView;

import com.test.trejo.jesus.grabilitytest.Model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus on 26/03/17.
 */

public interface IPopularFragment extends IView {

    public void showToast(String text);

    public void manageRecyclerView(List<Movie> movieList);

}
