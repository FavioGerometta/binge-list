package com.xing.binge.screens

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.xing.binge.R
import com.xing.binge.databinding.ActivityFavoritesBinding
import com.xing.binge.model.Data
import com.xing.binge.screens.adapters.FavouriteAdapter
import com.xing.binge.util.gone


class FavoritesActivity : AppCompatActivity() {
    companion object {
        val EXTRA = "FAVS"
        val KEY = 9
    }
    lateinit var binding: ActivityFavoritesBinding
    lateinit var favList : List<Data>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorites)

        favList = intent.getParcelableArrayListExtra<Parcelable>(EXTRA) as List<Data>
        if(favList == null){//initializeList()
            binding.favouriteList.textViewMovieListCallToAction.text = getString(R.string.favorites_empty_place_holder)
        } else{
            binding.favouriteList.textViewMovieListCallToAction.gone = true
            initializeList()
        }
    }

    private fun initializeList() {
        binding.favouriteList.recyclerViewMovieList.layoutManager = GridLayoutManager(this,2)
        binding.favouriteList.recyclerViewMovieList.adapter = FavouriteAdapter(favList)
    }

    override fun onBackPressed() {

        //super.onBackPressed()
        val intent = Intent()
        //pass back the items that are non favs now, if any
        intent.putParcelableArrayListExtra(EXTRA, favList.filter { item -> !item.isFavourite } as ArrayList<Parcelable>)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
