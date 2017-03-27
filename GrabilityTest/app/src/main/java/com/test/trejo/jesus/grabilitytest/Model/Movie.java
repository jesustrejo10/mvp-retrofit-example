package com.test.trejo.jesus.grabilitytest.Model;

import com.test.trejo.jesus.grabilitytest.Model.IModel.IMovie;

/**
 * Created by jesus on 26/03/17.
 */

public class Movie extends Entity implements IMovie {

    String name;

    public Movie ( int id, String name){
        super(id);
        this.name = name;
    }

    @Override
    public Movie getMovie(String name) {
        return null;
    }
}
