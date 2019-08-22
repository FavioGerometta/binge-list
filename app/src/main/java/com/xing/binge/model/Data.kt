package com.xing.binge.model
//added one parameter to handle presentation - could be separated with a DTO and a different model object
data class Data (val id: String, val title: String,val rating: String,val poster: Poster, var showStar: Boolean?)