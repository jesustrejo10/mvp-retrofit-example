package com.test.trejo.jesus.grabilitytest.View;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.test.trejo.jesus.grabilitytest.Helper.OnFragmentSwap;
import com.test.trejo.jesus.grabilitytest.Model.Movie;
import com.test.trejo.jesus.grabilitytest.Presentator.IPresentator.IMoviesListPresentator;
import com.test.trejo.jesus.grabilitytest.Presentator.MoviesListPresentator;
import com.test.trejo.jesus.grabilitytest.R;
import com.test.trejo.jesus.grabilitytest.View.IView.IMoviesListFragment;

import java.util.List;

public class MoviesListFragment extends Fragment implements IMoviesListFragment {

    private String DEFAULT_SEARCH = "popular";
    private int mPage = 1;
    private List<Movie> mListMovie;
    private IMoviesListPresentator presentator;
    private RecyclerView mRecyclerView;
    private SwipyRefreshLayout mSwipyRefreshLayout;
    private View mView;
    private MoviesAdapter mAdapter;
    private Bundle mBundle;
    private String mType;
    public OnFragmentSwap mCallBack;
    private Toolbar toolbar;

    public MoviesListFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallBack = (OnFragmentSwap) activity;
        } catch (ClassCastException e) {


            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_movies_list, container, false);
        try{

            setupViewValues();

            mBundle = getArguments();
            if (mBundle == null){
                mType = DEFAULT_SEARCH;
            }else{
                mType = mBundle.getString("type");
            }

            presentator = new MoviesListPresentator(this,getContext());
            managePrincipalInformation();

        }catch (Exception e){
            e.printStackTrace();
        }


        return mView;
    }

    private void managePrincipalInformation() {

        try{

            switch (mType){
                case "popular":
                    presentator.getPopularMovies(getPage());
                    toolbar.setTitle("Popular Movies");

                    break;
                case "top_rated":
                    presentator.getTopRatedMovies(getPage());
                    toolbar.setTitle("Top Rated Movies");

                    break;
                case "upcoming":
                    presentator.getUpcomingMovies(getPage());
                    toolbar.setTitle("Upcoming Movies");

                    break;
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /* R E C Y C L E R  V I E W  M E T H O D S*/

    private void setupViewValues(){
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mSwipyRefreshLayout = (SwipyRefreshLayout) mView.findViewById(R.id.swipyrefreshlayout);
        manageEventFromSwipy();
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

    }

    private void manageEventFromSwipy() {
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                //pido mas pelis!
                managePrincipalInformation();


            }
        });

    }

    /* END  RECYCLER VIEW METHODS*/

    @Override
    public void manageRecyclerView(List<Movie> movieList){
        try {
            if (mAdapter != null && mRecyclerView != null) {
                mListMovie.addAll(movieList);
                presentator.sendMoviesToDao(mListMovie,mType);
                mAdapter.notifyDataSetChanged();
                if (mAdapter.getItemCount() > 1) {
                    mRecyclerView.getLayoutManager().smoothScrollToPosition(mRecyclerView, null, mAdapter.getItemCount() - 20);
                }
            } else {
                mListMovie = movieList;
                presentator.sendMoviesToDao(mListMovie,mType);

                mAdapter = new MoviesAdapter(mListMovie, R.id.recycler_view, getContext(), mCallBack);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mAdapter);

            }
            incrementPage();
            stopLoading();
        }catch (Exception e){
            e.printStackTrace();

            mListMovie = movieList;
            mAdapter = new MoviesAdapter(mListMovie, R.id.recycler_view, getContext(), mCallBack);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(mAdapter);
            incrementPage();
            stopLoading();
        }

    }

    @Override
    public void manageRecyclerViewWithOutInternet(List<Movie> movieList) {
        try {
            if (mListMovie != null) {

                if (movieList != null)
                    if (movieList.size() > 1) {
                        if ( movieList.size() == mListMovie.size()){
                            stopLoading();
                            showToast("To get More Movies You need Internet");
                        }else{
                            mListMovie = movieList;
                            mAdapter = new MoviesAdapter(mListMovie, R.id.recycler_view, getContext(), mCallBack);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mRecyclerView.setAdapter(mAdapter);
                            mPage = mListMovie.size() / 20 + 1;
                            if (mPage == 1)
                                showToast("To get More Movies You need Internet");
                            stopLoading();
                        }
                    } else
                        showToast("To get More Movies You need Internet");
                stopLoading();
            } else {
                mListMovie = movieList;
                mAdapter = new MoviesAdapter(mListMovie, R.id.recycler_view, getContext(), mCallBack);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(mAdapter);
                mPage = mListMovie.size() / 20 + 1;
                if (mPage == 1)
                    showToast("To get More Movies You need Internet");
                stopLoading();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String getType() {
        if (mType != null)
            return mType;
        else
            return "";
    }

    @Override
    public int getPage() {
        return mPage;
    }

    @Override
    public void incrementPage() {
        mPage= mPage+1;
    }

    @Override
    public void stopLoading() {
        mSwipyRefreshLayout.setRefreshing(false);
    }


}
