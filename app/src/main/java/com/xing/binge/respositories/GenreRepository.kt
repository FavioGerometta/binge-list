package com.xing.binge.respositories

import com.xing.binge.api.MoviesApi
import com.xing.binge.model.Genre

class GenreRepository (private val api : MoviesApi) : BaseRepository() {

    suspend fun getGenres() : MutableSet<Genre>?{

        ///safeApiCall is defined in [BaseRepository.kt]
        val movieResponse = safeApiCall(
            call = {api.getGenres().await()},
            errorMessage = "Error Fetching Genres"
        )
        //return movieResponse?.results?.toMutableSet()
        return movieResponse?.toMutableSet()
    }

}