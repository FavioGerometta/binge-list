package com.xing.binge.respositories

import android.util.Log

class MovieMetadata {private constructor()
    //TODO: change in the future! late init inside companion throws error
    // , apparently something that will be fixed in later versions of Kotlin ¯\_(ツ)_/¯
    companion object {
        @Volatile private var INSTANCE: MovieMetadata? = null
        fun getInstance(): MovieMetadata =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MovieMetadata().also { INSTANCE = it }
            }
    }

    var offset: Int = 0
    val limit: Int = 20
    var genre: String? = null
}