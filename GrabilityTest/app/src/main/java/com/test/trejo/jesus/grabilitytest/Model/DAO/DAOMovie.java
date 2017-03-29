package com.test.trejo.jesus.grabilitytest.Model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.test.trejo.jesus.grabilitytest.Model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesus on 29/03/17.
 */

public class DAOMovie implements IDAOMovie {

    /**
     * Funcion encargada de solicitar una lista de peliculas dado el tipo contra la BD
     * @param context Parametro de entrada que contiene el Contexto de la app
     * @param type Parametro de entrada de tipo String, aca se especifica
     *             si se desean peliculas del tipo "polular,top-rated,upcoming"
     * @return Retorna una Lista de Peliculas de tipo List<Movie>
     */
    @Override
    public List<Movie> FetchMovieByType(Context context, String type) {
        DAO.MovieReaderDbHelper mDbHelper = new DAO.MovieReaderDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                DAO.MovieEntry._ID,
                DAO.MovieEntry.COLUMN_NAME_EXTERNAL_ID,
                DAO.MovieEntry.COLUMN_NAME_TITLE,
                DAO.MovieEntry.COLUMN_NAME_OVERVIEW,
                DAO.MovieEntry.COLUMN_NAME_RELEASE_DATE,
                DAO.MovieEntry.COLUMN_NAME_TYPE,
                DAO.MovieEntry.COLUMN_NAME_VOTE_AVERAGE,

        };

        String selection = DAO.MovieEntry.COLUMN_NAME_TYPE + " = ?";
        String[] selectionArgs = { type };

        String sortOrder =
                DAO.MovieEntry.COLUMN_NAME_RELEASE_DATE + " DESC";

        Cursor c = db.query(
                DAO.MovieEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        Movie movie;
        List<Movie> mList = new ArrayList<Movie>();


        while (c.moveToNext()) {
            //aca vamos a armar el objeto
            int externalId = c.getInt(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_EXTERNAL_ID));
            String title = c.getString(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_TITLE));
            String overview = c.getString(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_OVERVIEW));
            String date = c.getString(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_RELEASE_DATE));
            Double voteAverage = Double.parseDouble(c.getString(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_VOTE_AVERAGE)));

            movie = new Movie(externalId,title,overview,date,voteAverage);
            mList.add(movie);

        }

        System.out.println("pechoCuadrado");
        return (mList);

    }

    /**
     * Funcion encargada de Eliminar todos los registros de la base de datos dependiendo
     * del tipo de la pelicula "polular,top-rated,upcoming"
     * @param context Parametro de entrada que contiene el Contexto de la App
     * @param type Parametro de entrada donde se especifica el tipo de pelicula.
     */
    @Override
    public void RemoveMovieByType(Context context, String type) {
        DAO.MovieReaderDbHelper mDbHelper = new DAO.MovieReaderDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String selection = DAO.MovieEntry.COLUMN_NAME_TYPE + " = ?";
        String[] selectionArgs = { type };

        db.delete(DAO.MovieEntry.TABLE_NAME, selection, selectionArgs);
    }

    /**
     * Metodo encargado de insertar en base de datos una lista de peliculas
     * dependiendo de el tipo que sean "polular,top-rated,upcoming"
     * @param context Parametro de entrada que contiene el Contexto de la App
     * @param movies Parametro de entrada que contiene la lista de pelicuals que se agregaran
     *               en la Base de datos
     * @param type "polular,top-rated,upcoming"
     */
    @Override
    public void AddMovies(Context context, List<Movie> movies, String type) {
        DAO.MovieReaderDbHelper mDbHelper = new DAO.MovieReaderDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        RemoveMovieByType(context,type);

        for (Movie movie : movies){
            ContentValues values = new ContentValues();

            values.put(DAO.MovieEntry.COLUMN_NAME_TITLE, movie.getTitle());
            values.put(DAO.MovieEntry.COLUMN_NAME_OVERVIEW, movie.getOverview());
            values.put(DAO.MovieEntry.COLUMN_NAME_RELEASE_DATE, movie.getReleaseDate());
            values.put(DAO.MovieEntry.COLUMN_NAME_VOTE_AVERAGE, movie.getVoteAverage()+"");
            values.put(DAO.MovieEntry.COLUMN_NAME_IMG_PATH, movie.getPosterPath());
            values.put(DAO.MovieEntry.COLUMN_NAME_TYPE, type);
            values.put(DAO.MovieEntry.COLUMN_NAME_EXTERNAL_ID, movie.getId()+"");

            long newRowId = db.insert(DAO.MovieEntry.TABLE_NAME, null, values);

        }



    }

    /**
     * Funcion encargadade solicutar una pelicula en especifico dependiendo
     * de su Id
     * @param context Parametro de entrada que contiene el Contexto de la App
     * @param id Parametro de entrada que contiene el External Id (id de la pelicula
     *           segun el Servicio web)
     * @return Retorna un objeto de tipo Movie
     */
    @Override
    public Movie FetchMovieById(Context context , String id) {
        DAO.MovieReaderDbHelper mDbHelper = new DAO.MovieReaderDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String[] projection = {
                DAO.MovieEntry._ID,
                DAO.MovieEntry.COLUMN_NAME_EXTERNAL_ID,
                DAO.MovieEntry.COLUMN_NAME_TITLE,
                DAO.MovieEntry.COLUMN_NAME_OVERVIEW,
                DAO.MovieEntry.COLUMN_NAME_RELEASE_DATE,
                DAO.MovieEntry.COLUMN_NAME_TYPE,
                DAO.MovieEntry.COLUMN_NAME_VOTE_AVERAGE,

        };

        String selection = DAO.MovieEntry.COLUMN_NAME_EXTERNAL_ID + " = ?";
        String[] selectionArgs = { id };

        String sortOrder =
                DAO.MovieEntry.COLUMN_NAME_RELEASE_DATE + " DESC";

        Cursor c = db.query(
                DAO.MovieEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        Movie movie = null;


        while (c.moveToNext()) {
            int externalId = c.getInt(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_EXTERNAL_ID));
            String title = c.getString(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_TITLE));
            String overview = c.getString(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_OVERVIEW));
            String date = c.getString(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_RELEASE_DATE));
            Double voteAverage = Double.parseDouble(c.getString(c.getColumnIndexOrThrow(DAO.MovieEntry.COLUMN_NAME_VOTE_AVERAGE)));

            movie = new Movie(externalId,title,overview,date,voteAverage);

        }

        return (movie);
    }
}
