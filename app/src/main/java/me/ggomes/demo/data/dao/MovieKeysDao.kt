package me.ggomes.demo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.ggomes.demo.models.MovieKeys

@Dao
interface MovieKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieKeys(movieKey: MovieKeys)

    @Query("SELECT * FROM MovieKeys ORDER BY id DESC")
    suspend fun getMovieKeys(): List<MovieKeys>
}