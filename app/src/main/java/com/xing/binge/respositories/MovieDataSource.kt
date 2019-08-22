package com.xing.binge.respositories


import androidx.paging.PageKeyedDataSource
import com.xing.binge.api.ApiFactory
import com.xing.binge.api.ErrorManager

import com.xing.binge.model.Data

import com.xing.binge.util.JobProvider

import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

const val Key = 1
class MovieDataSource  : PageKeyedDataSource<Int, Data>(), JobProvider {

    lateinit var list:  List<Data>


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Data>) {
        scope.launch {
                val response = ApiFactory.moviesApi.getMovies(MovieMetadata.getInstance().genre!!,
                    0, MovieMetadata.getInstance().limit).await() //TODO:cambiar genero
                when{
                    response.isSuccessful -> {
                        val data = response.body()?.data
                        list = data!!
                        //calculateMedian()
                        MovieMedianCalculator.getInstance().calculate(list)
                        callback.onResult(data ?: listOf(), null, Key + 1 )
                    }
                    else ->{
                        ErrorManager.getInstance().propagateError("Error while fetching data")
                    }
                }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        scope.launch {
            MovieMetadata.getInstance().offset =+ params.requestedLoadSize
            val response = ApiFactory.moviesApi.getMovies(MovieMetadata.getInstance().genre!!,
                MovieMetadata.getInstance().offset, MovieMetadata.getInstance().limit).await() //TODO:cambiar genero
            when{
                response.isSuccessful -> {
                    val data = response.body()?.data
                    //Android indoor implementation uses a diff to check upon updates so getting back
                    //all the pages with changes instead of just one page at a time
                    list = list.union(data!!).toList()
                    MovieMedianCalculator.getInstance().calculate(list)
                    //TODO: remove
                    Timer().schedule(2000){
                        // add a delay is if it came from server and NOT mocked data
                        callback.onResult(list ?: listOf(),  null)//TODO: change null for subsequent key page in real life scenario as in NOT MOCKED: params.key.inc()
                    }
                }
                else ->{
                    ErrorManager.getInstance().propagateError("Error while fetching data")
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        //"not implemented")
    }
}