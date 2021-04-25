package me.ggomes.demo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.ggomes.demo.data.dao.MovieDao
import me.ggomes.demo.data.dao.MovieDetailDao
import me.ggomes.demo.data.dao.MovieKeysDao
import me.ggomes.demo.models.Movie
import me.ggomes.demo.models.MovieDetail
import me.ggomes.demo.models.MovieKeys

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