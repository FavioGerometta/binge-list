package com.xing.binge.vm

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

import com.xing.binge.model.Data

import com.xing.binge.respositories.MovieDataSource

class MovieViewModel: BaseViewModel()  {

    var moviesLiveData : LiveData<PagedList<Data>>
    lateinit var  movieDataSource : MovieDataSource
    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        moviesLiveData  = initializedPagedListBuilder(config).build()
    }

    fun getMovies(): LiveData<PagedList<Data>> = moviesLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Data> {

        val dataSourceFactory = object : DataSource.Factory<Int, Data>() {
            override fun create(): DataSource<Int, Data> {
                movieDataSource = MovieDataSource()
                return movieDataSource
            }
        }

        return LivePagedListBuilder(dataSourceFactory, config)
    }

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

}

