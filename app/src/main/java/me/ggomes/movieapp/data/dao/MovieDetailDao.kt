package me.ggomes.movieapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.ggomes.movieapp.models.MovieDetail

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieDetail: MovieDetail)

    @Query("SELECT * FROM MovieDetail")
    fun getAllDetails(): LiveData<List<MovieDetail>>

    @Query("SELECT * FROM MovieDetail WHERE id=:id")
    fun getDetailsBy(id: String): LiveData<MovieDetail>

    @Query("DELETE FROM MovieDetail WHERE id=:id")
    fun delete(id: String)
}