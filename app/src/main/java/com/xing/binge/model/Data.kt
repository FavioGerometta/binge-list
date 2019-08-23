package com.xing.binge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//added one parameter to handle presentation - could be separated with a DTO and a different model object
@Parcelize
data class Data (val id: String, val title: String,val rating: String,val poster: Poster, var showStar: Boolean?, var isFavourite : Boolean = false)  :
    Parcelable