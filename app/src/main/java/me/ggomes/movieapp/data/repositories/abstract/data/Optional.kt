package me.ggomes.movieapp.data.repositories.abstract.data

sealed class Optional<out T: Any> {
    data class Success<out T: Any>(val result: T): Optional<T>()
    data class Error(val throwable: Throwable): Optional<Nothing>()
}