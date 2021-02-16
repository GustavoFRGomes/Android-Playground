package me.ggomes.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.coroutineScope
import me.ggomes.movieapp.data.database.MovieDatabase
import me.ggomes.movieapp.data.dto.MovieDetailsResponse
import me.ggomes.movieapp.data.dto.MovieResponse
import me.ggomes.movieapp.data.network.RetrofitApiClient
import me.ggomes.movieapp.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}