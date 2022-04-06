package com.kotlin.myapplication.models.item

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize


/**
 * Created by @erickrenata on 04/04/22.
 */

@Parcelize
@Entity(
    tableName = "movies"
)
data class MovieItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val title: String? = "",
    val originalTitle: String? = "",
    val date: String? = "",
    val backdropImage: String? = "",
    val posterImage: String? = "",
    val description: String? = "",
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
    val language: String? = "",
    var isLiked: Boolean = false
) : Parcelable {
    @Ignore
    constructor() : this(0)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load("http://image.tmdb.org/t/p/w185$url").into(view)
    }
}