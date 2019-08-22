package com.xing.binge.vm

import androidx.lifecycle.MutableLiveData

import com.xing.binge.api.ApiFactory

import com.xing.binge.model.Genre
import com.xing.binge.respositories.GenreRepository

import kotlinx.coroutines.*


// as no UI is involved on this one, perhaps a better fit for Firebase to offload the logic yet leaving it as it is as
// wouldn't know for sure if this call might be needed in the future
// TODO: evaluate the necessity of this one as a coroutine
class GenreViewModel : BaseViewModel() {

    private val repository : GenreRepository = GenreRepository(ApiFactory.moviesApi)

    val genresLiveData = MutableLiveData<MutableSet<Genre>>()
    fun fetchGenres(){
        scope.launch {
            val genres = repository.getGenres()
            genresLiveData.postValue(genres)
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }
}