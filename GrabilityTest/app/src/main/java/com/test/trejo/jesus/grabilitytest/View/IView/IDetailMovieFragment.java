package com.test.trejo.jesus.grabilitytest.View.IView;

/**
 * Created by jesus on 28/03/17.
 */

/**
 * Contrato de la vista
 */
public interface IDetailMovieFragment {

    /**
     * Metodo Encargado de asignar el Titulo de la pelicula en la vista
     * @param name recibe el Titulo de la pelicula
     */
    void setMovieName ( String name );

    /**
     * Metodo encargado de asignar la imagen de la pelicula en la vista
     * @param Uri Recibe el URL en el que se encuentra almacenada la imagen
     */
    void setMovieImage ( String Uri );

    /**
     * Metodo encargado de asignar la descripcion de la pelicula en la vista
     * @param description recibe el Titulo de la pelicula
     */
    void setMovieDescription ( String description);

    /**
     * Metodo encargado de asignar la fecha de lanzamiento de la pelicula en la vista
     * @param date fecha de salida
     */
    void setMovieDate( String date );


    /**
     * Metodo encargado de asignar el Rate de la vista
     * @param rate recibe el Rate
     */
    void setMovieRate( String rate );

}
