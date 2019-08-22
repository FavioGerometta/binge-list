package com.xing.binge.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class ErrorManager {    private constructor()
    //TODO: change in the future! late init inside companion throws error
    // , apparently something that will be fixed in later versions of Kotlin ¯\_(ツ)_/¯
    companion object {
        @Volatile private var INSTANCE: ErrorManager? = null
        fun getInstance(): ErrorManager =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ErrorManager().also { INSTANCE = it }
            }
    }

    private var errorLiveData = MutableLiveData<String>()

    fun propagateError(errorMessage : String){
        Log.d("Propagating error:", errorMessage)
        errorLiveData.postValue(errorMessage)
    }

    fun observe(observer: Observer<String>) {
        errorLiveData.observeForever(observer)
    }
}