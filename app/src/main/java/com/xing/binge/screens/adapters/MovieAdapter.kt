package com.xing.binge.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xing.binge.R
import com.xing.binge.databinding.ListitemMovieBinding
import com.xing.binge.model.Data
import com.xing.binge.util.DiffUtilCallBack
import com.xing.binge.util.setImageUrl
import com.xing.binge.util.visible
import kotlinx.android.synthetic.main.listitem_movie.view.*

//listener could be elsewhere but safer on the constructor
class MovieAdapter(val clickListener: (Data) -> Unit): PagedListAdapter<Data, MovieAdapter.MovieViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bindMovie(it) }
    }

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val binding: ListitemMovieBinding? = DataBindingUtil.bind(itemView)

        init {
            itemView.tag = binding
        }

        fun bindMovie(movieData : Data){
            with(movieData){
                binding?.imageViewMovieListItemImage?.setImageUrl(poster.fullPath) //image
                binding?.imageViewMovieListItemStar?.visible = showStar!! //star rating
                itemView.imageView_movieListItem_favorite.isActivated = movieData.isFavourite //is favourite
                itemView.imageView_movieListItem_favorite.setOnClickListener{
                    itemView.imageView_movieListItem_favorite.isActivated = ! itemView.imageView_movieListItem_favorite.isActivated //change is favourite
                    movieData.isFavourite = itemView.imageView_movieListItem_favorite.isActivated // store change
                    clickListener(movieData) // call listener to maintain list of favourites
                }
            }
        }
    }

    fun removeFavourite(movieId:String){
        val position = currentList?.snapshot()?.indexOfFirst { data ->  data.id == movieId}
        currentList?.snapshot()?.forEach { movie -> if (movie.id == movieId) movie.isFavourite = false}
        currentList?.snapshot()?.get(position!!)?.isFavourite = false
        notifyItemChanged(position!!)
    }


}