package me.ggomes.movieapp.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.ggomes.movieapp.models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("SELECT * FROM Movie")
    fun getAllMovies(): PagingSource<Int, Movie>

    @Query("SELECT * FROM Movie WHERE id=:id")
    suspend fun getBy(id: String): Movie

    @Query("SELECT * FROM Movie WHERE title LIKE :term")
    suspend fun searchBy(term: String): List<Movie>

    @Query("DELETE FROM movie WHERE id=:id")
    suspend fun delete(id: String)
}