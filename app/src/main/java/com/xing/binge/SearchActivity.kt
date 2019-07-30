package com.xing.binge

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {

    /* properties */
    private val genreSet : Set<String>? by lazy{
        resources.getStringArray(R.array.genres).map { s: String -> s.toLowerCase() }.toSet()
    }

    private val searchEditText : EditText by lazy{
        findViewById(R.id.editText_search)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        configureSearchInputListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_favorite -> {
               startActivity(Intent(this, FavoritesActivity::class.java))
            }
        }
        return true
    }

    private fun configureSearchInputListeners() {
        searchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchEditText.text?.toString()?.let {
                        performSearch(it)
                    }
                }
                return false
            }
        })
        findViewById<Button>(R.id.button_search).setOnClickListener {
            searchEditText.text?.toString()?.let {
                performSearch(it)
                hideSoftKeyboard()
            }
        }
    }

    private fun performSearch(searchText: String) {
        val genreSearchResultPair = genreSet?.search(searchText.toLowerCase())
        if(genreSearchResultPair?.first!!){
            longToast("Found a match [GENRE]:  ${genreSearchResultPair?.second}")
        }else{
            longToast("No match found [GENRE] with the following search text: $searchText")
        }
    }
}

//extension function
private fun Set<String>?.search(lowerCasedSearchText: String): Pair<Boolean,String?> {
    //split into a list based on found spaces
    val wordList = lowerCasedSearchText.split("\\s+".toRegex())

    //if the 2 words don't match then only care about finding a match with the first one
    fun findTwoWordMatch(): String? {
        var result = this?.firstOrNull { s: String -> s == (wordList.first() + " " + wordList.elementAtOrNull(1)) }
        if (result.isNullOrEmpty()) result = this?.firstOrNull { s: String -> s == wordList.first() }
        return result
    }

    fun findMatchingGenre(): Pair<Boolean,String?> {
        val genre = when {
            wordList.size > 1 -> findTwoWordMatch()
            else -> this?.firstOrNull { s: String ->  s == wordList.elementAtOrNull(0)}
        }
        //build a pair as (a match was found, string that matches)
        return Pair(!genre.isNullOrEmpty(),genre)
    }
    //init
    return findMatchingGenre()
}



