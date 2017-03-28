package com.test.trejo.jesus.grabilitytest.View;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.test.trejo.jesus.grabilitytest.Model.Movie;
import com.test.trejo.jesus.grabilitytest.Presentator.IPresentator.IPopularPresentator;
import com.test.trejo.jesus.grabilitytest.Presentator.PopularPresentator;
import com.test.trejo.jesus.grabilitytest.R;
import com.test.trejo.jesus.grabilitytest.View.IView.IPopularFragment;
import com.test.trejo.jesus.grabilitytest.View.IView.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PopularFragment extends Fragment implements IPopularFragment, IView {

    public PopularFragment() {
    }

    IPopularPresentator presentator;
    private Button mButton;
    private RecyclerView mRecyclerView;
    private View mView;
    private MoviesAdapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_principal, container, false);
        mButton = (Button) mView.findViewById(R.id.click_here);
        presentator = new PopularPresentator(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentator.getName();
                presentator.getPopularMovies();
            }
        });
        setupViewValues();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /* R E C Y C L E R  V I E W  M E T H O D S*/

    private void setupViewValues(){
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
    }


    @Override
    public void manageRecyclerView(List<Movie> movieList){
        mAdapter = new MoviesAdapter( movieList , R.id.recycler_view , getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);


    }


}
