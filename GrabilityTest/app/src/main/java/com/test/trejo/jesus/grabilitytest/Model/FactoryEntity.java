package com.test.trejo.jesus.grabilitytest.Model;

/**
 * Created by jesus on 26/03/17.
 */

public class FactoryEntity {

    public static Movie getInstanceOfMovie(int id, String name){
        return new Movie(id,name);
    }
}
