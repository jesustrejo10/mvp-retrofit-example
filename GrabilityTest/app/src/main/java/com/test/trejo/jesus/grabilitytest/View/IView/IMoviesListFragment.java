package com.test.trejo.jesus.grabilitytest.View.IView;

import com.test.trejo.jesus.grabilitytest.Model.Movie;

import java.util.List;

/**
 * Created by jesus on 26/03/17.
 */

public interface IMoviesListFragment {

    /**
     * Metodo encargado de mostrar un Toast en pantalla
     * @param text texto que sera mostrado en el Toast
     */
    void showToast(String text);

    /**
     * Metodo encargado de asignar la lista de pelicular recibidas
     * por el presentador en la vista (cuando hay conexion con el API)
     * @param movieList Lista de peliculas que seran a;adidas a la vista
     */
    void manageRecyclerView(List<Movie> movieList);

    /**
     * Metodo encargado de asignar la lista de pelicular recibidas
     * por el presentador en la vista (cuando hay conexion con la Base de Datos)
     * @param movieList Lista de peliculas que seran a;adidas a la vista
     */
    void manageRecyclerViewWithOutInternet(List<Movie> movieList);

    /**
     * Metodo encargado de devolver el tipo de pelicula segun el Fragment
     * @return Retorna el tipo de pelicula
     */
    String getType();

    /**
     * Metodo encargado de obtener la Proxima pagina de peliculas
     * @return
     */
    int getPage();

    /**
     * Metodo encargado de incrementar la pagina de peliculas
     */
    void incrementPage();

    /**
     * Metodo que permite detener la visualizacion de la ventana de "cargando"
     */
    void stopLoading();
}
