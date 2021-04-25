package me.ggomes.demo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.ggomes.demo.models.MovieDetail

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieDetail: MovieDetail)

    @Query("SELECT * FROM MovieDetail")
    suspend fun getAllDetails(): List<MovieDetail>

    @Query("SELECT * FROM MovieDetail WHERE id=:id")
    suspend fun getDetailsBy(id: String): MovieDetail?

    @Query("DELETE FROM MovieDetail WHERE id=:id")
    suspend fun delete(id: String)
}