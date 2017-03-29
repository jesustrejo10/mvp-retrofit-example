package com.test.trejo.jesus.grabilitytest.Model.DAO;

import android.content.Context;

import com.test.trejo.jesus.grabilitytest.Model.Movie;

import java.util.List;

/**
 * Created by jesus on 29/03/17.
 */

public interface IDAOMovie {

    /**
     * Funcion encargada de solicitar una lista de peliculas dado el tipo contra la BD
     * @param context Parametro de entrada que contiene el Contexto de la app
     * @param type Parametro de entrada de tipo String, aca se especifica
     *             si se desean peliculas del tipo "polular,top-rated,upcoming"
     * @return Retorna una Lista de Peliculas de tipo List<Movie>
     */
    List<Movie> FetchMovieByType(Context context,String type);


    /**
     * Funcion encargada de Eliminar todos los registros de la base de datos dependiendo
     * del tipo de la pelicula "polular,top-rated,upcoming"
     * @param context Parametro de entrada que contiene el Contexto de la App
     * @param type Parametro de entrada donde se especifica el tipo de pelicula.
     */
    void RemoveMovieByType(Context context,String type);

    /**
     * Metodo encargado de insertar en base de datos una lista de peliculas
     * dependiendo de el tipo que sean "polular,top-rated,upcoming"
     * @param context Parametro de entrada que contiene el Contexto de la App
     * @param movies Parametro de entrada que contiene la lista de pelicuals que se agregaran
     *               en la Base de datos
     * @param type "polular,top-rated,upcoming"
     */
    void AddMovies(Context context,List<Movie> movies, String type);

    /**
     * Funcion encargadade solicutar una pelicula en especifico dependiendo
     * de su Id
     * @param context Parametro de entrada que contiene el Contexto de la App
     * @param id Parametro de entrada que contiene el External Id (id de la pelicula
     *           segun el Servicio web)
     * @return Retorna un objeto de tipo Movie
     */
    Movie FetchMovieById(Context context , String id);

}
