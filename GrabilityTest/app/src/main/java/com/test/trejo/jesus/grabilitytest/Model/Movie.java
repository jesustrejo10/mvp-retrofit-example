package com.test.trejo.jesus.grabilitytest.Model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus on 26/03/17.
 */

/**
 * Clase Movie, Aca se encuentra el Dominio de nuestra aplicacion
 * Los atributos se encuentran "anotados" para facilitar la construccion del Objeto
 * a partir de un Json utilizando la clase GSON
 */
public class Movie {

    String name;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("genre_ids")
    private List<Integer> genreIds = new ArrayList<Integer>();
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;


    /**
     * Constructor Original a partir del servicio web, se almacena
     * toda la informacion que es proporcionada por el mismo
     * @param posterPath Ruta de la Imagen
     * @param adult si la pelicula tiene contenido Pornografico
     * @param overview Resumen de la Pelicula
     * @param releaseDate Fecha de Estreno
     * @param genreIds Ids de los generos a los que aplica la pelicula
     * @param id Id de la pelicula segun el Servicio web
     * @param originalTitle Titulo Orignal de lanzamiento
     * @param originalLanguage Lenguage original de la pelicula
     * @param title Titulo de la Pelicula
     * @param backdropPath Ruta de la Imagen
     * @param popularity Popularidad de la pelicula
     * @param voteCount Cantidad de votos registrados
     * @param video Video de la pelicula o trailer
     * @param voteAverage En porcentaje que tan buela es la pelicula
     */
    public Movie(String posterPath, boolean adult, String overview, String releaseDate, List<Integer> genreIds, Integer id,
                 String originalTitle, String originalLanguage, String title, String backdropPath, Double popularity,
                 Integer voteCount, Boolean video, Double voteAverage) {
        //super(id);
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
    }

    /**
     * Constructor detalladado de la pelicula, se almacenan solo los datos
     * que seran mostrados en las vistas
     * @param id Id de la pelicula segun el servicio web
     * @param title Titulo de la pelicula
     * @param overview Resuman
     * @param releaseDate Fecha de lanzamiento
     * @param voteAverage Average de votos
     */
    public Movie(Integer id, String title, String overview, String releaseDate, Double voteAverage){
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;

    }


    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }





}
