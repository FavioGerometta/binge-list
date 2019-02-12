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
        val searchEditText = findViewById<EditText>(R.id.editText_search)
        searchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //TODO to be implemented
                }
                return false
            }
        })
        findViewById<Button>(R.id.button_search).setOnClickListener {
            searchEditText.text?.toString()?.let {
                //TODO to be implemented
                hideSoftKeyboard()
            }
        }
    }
}
