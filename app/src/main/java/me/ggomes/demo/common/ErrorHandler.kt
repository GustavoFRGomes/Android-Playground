package me.ggomes.demo.common

import android.content.Context
import me.ggomes.demo.R
import retrofit2.HttpException

class ErrorHandler(
    private val appContext: Context
) {

    fun handleException(exception: Exception): String {
        return when (exception) {
            is HttpException -> appContext.getString(R.string.error_network_data)
            else -> appContext.getString(R.string.error_data_retrieval)
        }
    }

}