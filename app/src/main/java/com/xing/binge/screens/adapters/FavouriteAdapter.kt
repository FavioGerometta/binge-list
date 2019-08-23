package com.xing.binge.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xing.binge.R
import com.xing.binge.databinding.ListitemMovieBinding
import com.xing.binge.model.Data
import com.xing.binge.util.setImageUrl
import com.xing.binge.util.visible
import kotlinx.android.synthetic.main.listitem_movie.view.*

class FavouriteAdapter(
    private var favouriteList: List<Data>): RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.listitem_movie, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.binding?.favouriteList = favouriteList[position]
        val movie: Data = favouriteList[position]
        holder.bindMovie(movie)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ListitemMovieBinding? = DataBindingUtil.bind(view)

        init {
            view.tag = binding
        }

        fun bindMovie(movieData : Data){
            with(movieData){
                binding?.imageViewMovieListItemImage?.setImageUrl(poster.fullPath) //image
                binding?.imageViewMovieListItemStar?.visible = showStar!! //star rating
                itemView.imageView_movieListItem_favorite.isActivated = movieData.isFavourite //is favourite
                itemView.imageView_movieListItem_favorite.setOnClickListener{
                    itemView.imageView_movieListItem_favorite.isActivated = ! itemView.imageView_movieListItem_favorite.isActivated //change is favourite
                    movieData.isFavourite = itemView.imageView_movieListItem_favorite.isActivated // store change
                }
            }
        }
    }
}