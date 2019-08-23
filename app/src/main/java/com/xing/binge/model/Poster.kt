package com.xing.binge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Poster(val fullPath: String,val size: String ) : Parcelable