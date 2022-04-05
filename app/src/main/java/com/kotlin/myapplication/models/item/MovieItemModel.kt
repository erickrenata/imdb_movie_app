package com.kotlin.myapplication.models.item

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize


/**
 * Created by @erickrenata on 04/04/22.
 */

@Parcelize
data class MovieItemModel(
    val id: Int?,
    val title: String?,
    val originalTitle: String?,
    val date: String?,
    val backdropImage: String?,
    val posterImage: String?,
    val description: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val language: String?
): Parcelable

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load("http://image.tmdb.org/t/p/w185$url").into(view)
    }
}