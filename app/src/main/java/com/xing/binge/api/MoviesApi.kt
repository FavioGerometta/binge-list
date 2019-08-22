package com.xing.binge.api

import com.xing.binge.model.Genre
import com.xing.binge.model.GenresResponse
import com.xing.binge.model.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("genres")
    fun getGenres() : Deferred<Response<List<Genre>>>

    @GET("movies")
    fun getMovies(@Query("genre") genre:String, @Query("offset") offset:Int, @Query("limit") limit:Int) : Deferred<Response<MoviesResponse>>
}