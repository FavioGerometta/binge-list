package com.xing.binge

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites) 

        findViewById<TextView>(R.id.textView_movieList_callToAction).setText(R.string.favorites_empty_place_holder)
    }
}
