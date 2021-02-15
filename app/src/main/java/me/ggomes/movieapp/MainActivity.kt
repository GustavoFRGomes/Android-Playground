package me.ggomes.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.ggomes.movieapp.data.dto.MovieDetailsResponse
import me.ggomes.movieapp.data.dto.MovieResponse
import me.ggomes.movieapp.data.network.RetrofitApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        RetrofitApiClient.openMovieDbService.getMovieBy("tt0831387").enqueue(object: Callback<MovieDetailsResponse> {
            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

        RetrofitApiClient.openMovieDbService.search("Godzilla").enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        */
    }
}