package com.test.trejo.jesus.grabilitytest.Model.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


/**
 * Created by jesus on 29/03/17.
 */

/**
 * Clase DAO, Encargada de realizar todas las operaciones de Base de Datos
 * (aca se coloca toda la configuracion)
 */
public class DAO {


    /**
     * Constructor vacio de la clase DAO
     */
    public DAO(){}


    /**
     * Clase publica statica MovieEntre, encargada de guardar los datos de la
     * estructura de la tabla
     */
    static class MovieEntry implements BaseColumns {

        static final String TABLE_NAME = "movie";
        static final String COLUMN_NAME_IMG_PATH = "backdrop_path";
        static final String COLUMN_NAME_TITLE = "original_title";
        static final String COLUMN_NAME_OVERVIEW = "overview";
        static final String COLUMN_NAME_RELEASE_DATE = "release_date";
        static final String COLUMN_NAME_TYPE = "type";
        static final String COLUMN_NAME_VOTE_AVERAGE = "vote_average";
        static final String COLUMN_NAME_EXTERNAL_ID = "movie_id";


    }


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DAO.MovieEntry.TABLE_NAME + " (" +
                    MovieEntry._ID + " INTEGER PRIMARY KEY," +
                    MovieEntry.COLUMN_NAME_EXTERNAL_ID + TEXT_TYPE + COMMA_SEP +
                    DAO.MovieEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    DAO.MovieEntry.COLUMN_NAME_OVERVIEW + TEXT_TYPE + COMMA_SEP +
                    DAO.MovieEntry.COLUMN_NAME_RELEASE_DATE + TEXT_TYPE + COMMA_SEP +
                    DAO.MovieEntry.COLUMN_NAME_VOTE_AVERAGE + TEXT_TYPE + COMMA_SEP +
                    DAO.MovieEntry.COLUMN_NAME_IMG_PATH + TEXT_TYPE + COMMA_SEP +
                    DAO.MovieEntry.COLUMN_NAME_TYPE + TEXT_TYPE +  " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DAO.MovieEntry.TABLE_NAME;


    /**
     * Clase publica estatica MovieReaderDbHelper
     * Encargada de ser el apoyo para realizar las consultas contra la BD
     */

    public static class MovieReaderDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 3;
        public static final String DATABASE_NAME = "MovieReader.db";

        public MovieReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

}
