package com.xing.binge.respositories

import com.xing.binge.model.Data


class MovieMedianCalculator{    private constructor()
    //TODO: change in the future! late init inside companion throws error
    // , apparently something that will be fixed in later versions of Kotlin ¯\_(ツ)_/¯
    companion object {
        @Volatile private var INSTANCE: MovieMedianCalculator? = null
        fun getInstance(): MovieMedianCalculator =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MovieMedianCalculator().also { INSTANCE = it }
            }
    }

    private var median:Float? = null

    private fun calculateMedian(list: List<Data>) {
        median =  median(list.map { it.rating.toFloat() })
    }

    fun median(l: List<Float>) = l.sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }

    fun calculate(list: List<Data>) {
        calculateMedian(list)
        list.forEach{item -> item.showStar = item.rating.toFloat()>= median!!}
    }
}