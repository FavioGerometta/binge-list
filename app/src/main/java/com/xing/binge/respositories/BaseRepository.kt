package com.xing.binge.respositories

import android.util.Log
import com.xing.binge.api.ErrorManager

import retrofit2.Response
import com.xing.binge.api.Result

import java.io.IOException

open class BaseRepository{
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                Log.d("1.BaseRepository", "$errorMessage & Exception - ${result.exception}")
                //
                ErrorManager.getInstance().propagateError(errorMessage) //result.exception
            }
        }
        return data

    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T>{
        val response = call.invoke()
        if(response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }

    companion object {
        const val maxItemsPerPage : Int = 20 //TODO: extract from config file
    }
}