package com.xing.binge.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.xing.binge.R
import com.xing.binge.api.ErrorManager
import com.xing.binge.databinding.ActivitySearchBinding
import com.xing.binge.model.Data
import com.xing.binge.respositories.MovieMetadata
import com.xing.binge.screens.adapters.MovieAdapter
import com.xing.binge.util.*
import com.xing.binge.vm.GenreViewModel
import com.xing.binge.vm.MovieViewModel
import java.util.*
import kotlin.collections.HashMap


class SearchActivity : AppCompatActivity() {

    private val favourites : MutableMap<String, Data> = HashMap()
    lateinit var binding: ActivitySearchBinding
    private val movieAdapter = MovieAdapter { item : Data -> favClicked(item) }

    private var genreSet: Set<String>? = null

    /* properties */
    private val searchButton : Button by lazy{
        binding.buttonSearch
    }

    private val searchEditText : EditText by lazy{
        binding.editTextSearch
    }

    private val errorText : TextView by lazy{
        binding.listContainer.textViewMovieListError
    }

    private val callToActionText : TextView by lazy{
        binding.listContainer.textViewMovieListCallToAction
    }

    private val genreViewModel by lazy {
        ViewModelProviders.of(this).get(GenreViewModel::class.java)
    }

    private val movieViewModel by lazy {
        ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        initializeList()
        configureListeners()
        observeGenres()
        observeErrors()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            com.xing.binge.R.id.menu_item_favorite -> {
                val intent = Intent(this, FavoritesActivity::class.java)
                intent.putParcelableArrayListExtra(
                    FavoritesActivity.EXTRA,
                    ArrayList<Parcelable>(favourites.values)
                )
                startActivityForResult(intent, FavoritesActivity.KEY)
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode === FavoritesActivity.KEY) {
            when(resultCode){
                Activity.RESULT_OK -> updateFavourites(data?.getParcelableArrayListExtra(FavoritesActivity.EXTRA))
                //Activity.RESULT_CANCELED -> not foreseen at the moment //TODO: could be deleted in next version
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun updateFavourites(parcelableArrayListExtra: ArrayList<Parcelable>?) {
        if(!parcelableArrayListExtra.isNullOrEmpty()){
            (parcelableArrayListExtra as ArrayList<Data>).forEach { movie -> updateFavouriteItem(movie.id) }
        }
    }

    private fun updateFavouriteItem(movieId: String) {
        removeItemFromFavourites(movieId)
        movieAdapter.removeFavourite(movieId)
    }

    private fun configureListeners() {
        //search launched by keyboard icon
        searchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    startGenreSearch(searchEditText.text)
                }
                return false
            }
        })
        //search launched by button
        searchButton.setOnClickListener {
            startGenreSearch(searchEditText.text, true)
        }
    }

    private fun startGenreSearch(editableText: Editable?, hideKeyboard : Boolean = false) {
        if(editableText.isNullOrEmpty())
            shortToast(getString(R.string.txt_edit_no_input))
        else{
            performSearch(editableText.toString())
            if(hideKeyboard)
                hideSoftKeyboard()
        }
    }

    private fun performSearch(searchText: String) {
        errorText.gone = true //TODO: add support for error showing
        callToActionText.gone = false
        val genreSearchResultPair = genreSet?.search(searchText.toLowerCase())
        if(genreSearchResultPair?.first!!){
            longToast("Found a match [GENRE]:  ${genreSearchResultPair.second}")
            MovieMetadata.getInstance().genre = genreSearchResultPair.second!!.capitalize()
            MovieMetadata.getInstance().offset = 0
            observeMovies()
        }else{
            longToast("No match found [GENRE] with the following search text: $searchText")
        }
    }

    private fun initializeList() {
        binding.listContainer.recyclerViewMovieList.layoutManager = GridLayoutManager(this,2)
        binding.listContainer.recyclerViewMovieList.adapter = movieAdapter
    }

    private fun observeMovies() {
        if(movieViewModel.moviesLiveData!=null) {
//            movieViewModel.moviesLiveData.value?.clear()
            movieViewModel.moviesLiveData.value?.dataSource?.invalidate()
        }
        movieViewModel.getMovies().observe(this, Observer {
            movieAdapter.submitList(it)
            callToActionText.gone = true
        })
    }

    private fun observeGenres() {
        genreViewModel.fetchGenres()
        genreViewModel.genresLiveData.observe(this, Observer {
            genreSet = it.map { genre ->  genre.name.toLowerCase()}.toSet()
        })
    }

    private fun observeErrors() {
        ErrorManager.getInstance().observe(changeObserverErrors)
    }

    private val changeObserverErrors = Observer<String> { value ->
        value?.let {
            errorText.text = it
            errorText.gone = false
        }
    }

    //another alternative could be not using this list and creating a getter in the adapter to return currentList and
    //get those that are checked in the snapshot as fav's
    private fun favClicked(item: Data) {
        when(favourites.contains(item.id)){
            true -> removeItemFromFavourites(item.id)
            false-> addItemToFavourites(item)
        }
    }

    private fun removeItemFromFavourites(itemId: String){
        favourites.remove(itemId)
    }

    private fun addItemToFavourites(item: Data){
        favourites[item.id] = item
    }

}





