package me.ggomes.movieapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.ggomes.movieapp.data.dao.MovieDao
import me.ggomes.movieapp.data.dao.MovieDetailDao
import me.ggomes.movieapp.models.Movie
import me.ggomes.movieapp.models.MovieDetail

@Database(entities = [Movie::class, MovieDetail::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun movieDetailDao(): MovieDetailDao

    companion object {
        private const val DB_NAME = "MovieDb"
        private var instance: MovieDatabase? = null
        private val lock = Any()

        fun getInstance(application: Application): MovieDatabase {
            synchronized(lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(application, MovieDatabase::class.java, DB_NAME)
                        .build()
                }
            }

            return instance!!
        }
    }
}