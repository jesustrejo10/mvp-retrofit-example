package com.test.trejo.jesus.grabilitytest.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.trejo.jesus.grabilitytest.Presentator.DetailMoviePresentator;
import com.test.trejo.jesus.grabilitytest.Presentator.IPresentator.IDetailMoviePresentator;
import com.test.trejo.jesus.grabilitytest.R;
import com.test.trejo.jesus.grabilitytest.View.IView.IDetailMovieFragment;

public class DetailMovieFragment extends Fragment implements IDetailMovieFragment{

    private Bundle mBundle;
    private View mView;
    private IDetailMoviePresentator mPresentator;

    private TextView mMovieNameTV;
    private TextView mMovieDescriptionTV;
    private ImageView mMovieImageIV;

    /**
     * Constructor Vacio de la Clase DetailMovieFragment
     */
    public DetailMovieFragment() {
    }

    /**
     * Metodo encargado de asignar los valores de la vista a sus respectivas
     * variables en codigo
     */
    private void setupViewValue(){
        mMovieNameTV = (TextView) mView.findViewById(R.id.movie_att1);
        mMovieDescriptionTV = (TextView) mView.findViewById(R.id.movie_att2);
        mMovieImageIV = (ImageView) mView.findViewById(R.id.movie_photo);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_detail_movie, container, false);

        mBundle= getArguments();
        setupViewValue();
        mPresentator = new DetailMoviePresentator(getContext(),this);
        mPresentator.getDetailMovie(mBundle.getInt("movieId"));
        return mView;

    }


    /**
     * Metodo encargado de asignar el valor al campo del titulo en la vista
     * @param name recibe el Titulo de la pelicula
     */
    @Override
    public void setMovieName(String name) {
        mMovieNameTV.setText(name);
    }

    /**
     * Meotodo encargado de cargar la imagen en la vista
     * @param Uri Recibe el URL en el que se encuentra almacenada la imagen
     */
    @Override
    public void setMovieImage(String Uri) {
        Picasso.with(getContext()).load(Uri).into(mMovieImageIV);

    }

    /**
     * Metodo encargado de asignar el titulo en la vista
     * @param description recibe el Titulo de la pelicula
     */
    @Override
    public void setMovieDescription(String description) {
        mMovieDescriptionTV.setText(description);
    }
}
