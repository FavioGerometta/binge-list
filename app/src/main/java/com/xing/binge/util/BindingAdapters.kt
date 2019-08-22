package com.xing.binge.util

import android.view.View
import android.view.View.*
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.xing.binge.R

@set:BindingAdapter("visibleOrGone")
var View.visibleOrGone
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

@set:BindingAdapter("visible")
var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else INVISIBLE
    }

@set:BindingAdapter("invisible")
var View.invisible
    get() = visibility == INVISIBLE
    set(value) {
        visibility = if (value) INVISIBLE else VISIBLE
    }

@set:BindingAdapter("gone")
var View.gone
    get() = visibility == GONE
    set(value) {
        visibility = if (value) GONE else VISIBLE
    }

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context).load(url).into(this)
}
