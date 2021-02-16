package me.ggomes.movieapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.ggomes.movieapp.data.dao.MovieDao
import me.ggomes.movieapp.data.dao.MovieDetailDao
import me.ggomes.movieapp.data.dao.MovieKeysDao
import me.ggomes.movieapp.models.Movie
import me.ggomes.movieapp.models.MovieDetail
import me.ggomes.movieapp.models.MovieKeys

@Database(entities = [Movie::class, MovieKeys::class, MovieDetail::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao
    abstract fun movieKeysDao(): MovieKeysDao

    companion object {
        private const val DB_NAME = "MovieDb"
        private var instance: MovieDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): MovieDatabase {
            synchronized(lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, DB_NAME)
                        .build()
                }
            }

            return instance!!
        }
    }
}