package com.test.trejo.jesus.grabilitytest.Presentator.IPresentator;

import com.test.trejo.jesus.grabilitytest.Model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus on 26/03/17.
 */

public interface IMoviesListPresentator {


    /**
     * Metodo que permite obtener una lista de peliculas del tipo "popular"
     * segun una pagina especifica
     * @param page Pagina que se desea obtener
     */
    void getPopularMovies(int page);

    /**
     * Metodo que permite obtener una lista de peliculas del tipo "top rated"
     * segun una pagina especifica
     * @param page Pagina que se desea obtener
     */
    void getTopRatedMovies(int page);

    /**
     * Metodo que permite obtener una lista de peliculas del tipo "Upcoming"
     * segun una pagina especifica
     * @param page Pagina que se desea obtener
     */
    void getUpcomingMovies(int page);

    /**
     * Metodo que permite enviar una lista de peliculas de tipo List<Movie>
     *     segun sea su tipo
     * @param mList Lista que contiene las peliculas que se desean guardar
     * @param type Tipo de peliculas que van en la lista
     */
    void sendMoviesToDao(List<Movie> mList, String type);

}
