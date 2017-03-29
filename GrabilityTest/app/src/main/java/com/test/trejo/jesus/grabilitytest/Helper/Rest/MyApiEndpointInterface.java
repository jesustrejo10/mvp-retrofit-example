package com.test.trejo.jesus.grabilitytest.Helper.Rest;

import com.test.trejo.jesus.grabilitytest.Model.Movie;
import com.test.trejo.jesus.grabilitytest.Model.MoviesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jesus on 26/03/17.
 */

public interface MyApiEndpointInterface {


    /**
     *Encargado de devolver las peliculas de tipo populares
     * @param apiKey Clave del API otorgado por la pagina de las peliculas
     * @param page Pagina de busqueda, cada pagina trae 20 registros
     * @return List<MoviesResponse> Lista de las peliculas
     */
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    /**
     *Encargado de devolver las peliculas de tipo top_rated
     * @param apiKey Clave del API otorgado por la pagina de las peliculas
     * @param page Pagina de busqueda, cada pagina trae 20 registros
     * @return List<MoviesResponse> Lista de las peliculas
     */
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    /**
     *Encargado de devolver las peliculas de tipo Upcoming
     * @param apiKey Clave del API otorgado por la pagina de las peliculas
     * @param page Pagina de busqueda, cada pagina trae 20 registros
     * @return List<MoviesResponse> Lista de las peliculas
     */
    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") int page);

    /**
     * Encargado de devolver el detalle de una pelicula dado 1 id
     * @param id Id de la pelicula que se desea buscar
     * @param apiKey Clave del API otorgado por la pagina de las peliculas
     * @return Retorna una Pelicula de tipo Movie
     */
    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") int id, @Query("api_key") String apiKey);

}
