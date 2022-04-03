package com.kotlin.myapplication.models.item

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


/**
 * Created by @erickrenata on 04/04/22.
 */

data class MovieItemViewModel(
    val title: String?,
    val date: String?,
    val urlImage: String?,
    val description: String?
)

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load("http://image.tmdb.org/t/p/w185$url").into(view)
    }
}