package com.xing.binge.util

import androidx.recyclerview.widget.DiffUtil
import com.xing.binge.model.Data

class DiffUtilCallBack : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.title == newItem.title
                && oldItem.rating == newItem.rating
                && oldItem.poster == newItem.poster
    }

}