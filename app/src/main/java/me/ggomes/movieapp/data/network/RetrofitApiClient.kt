package me.ggomes.movieapp.data.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import me.ggomes.movieapp.BuildConfig
import okhttp3.OkHttpClient
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiClient {
    private val appContext: Context by inject(Context::class.java)

    private val retrofit: Retrofit by lazy {
        val okhttpClient = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(appContext.applicationContext).build())
            .build()

        Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient)
                .build()
    }

    val openMovieDbService: OpenMovieDatabaseService by lazy {
        retrofit.create(OpenMovieDatabaseService::class.java)
    }
}