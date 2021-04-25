package me.ggomes.demo.views.utils

import me.ggomes.demo.models.Movie
import org.junit.Test

import org.junit.Before

class MovieDiffItemCallbackTest {

    private val SUT = MovieDiffItemCallback()

    private lateinit var movieA: Movie
    private lateinit var movieB: Movie
    private lateinit var sameAsMovieA: Movie
    private lateinit var onlySameIdAsB: Movie

    @Before
    fun setup() {
        movieA = Movie("id", "title", "2020", "url")
        sameAsMovieA = Movie("id", "title", "2020", "url")
        movieB = Movie("idB", "titleB", "2020B", "urlB")
        onlySameIdAsB = Movie("idB", "title", "2020", "url")
    }

    @Test
    fun areItemsTheSame() {
        assert(SUT.areItemsTheSame(movieA, sameAsMovieA))
        assert(SUT.areItemsTheSame(movieB, onlySameIdAsB))
        assert(
            SUT.areItemsTheSame(movieA, movieB)
                .not()
        )
    }

    @Test
    fun areContentsTheSame() {
        assert(SUT.areContentsTheSame(movieA, sameAsMovieA))
        assert(
            SUT.areContentsTheSame(movieB, onlySameIdAsB)
                .not()
        )
    }
}