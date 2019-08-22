package com.xing.binge.screens

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.xing.binge.R

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        findViewById<TextView>(R.id.textView_movieList_callToAction).setText(R.string.favorites_empty_place_holder)
    }
}
