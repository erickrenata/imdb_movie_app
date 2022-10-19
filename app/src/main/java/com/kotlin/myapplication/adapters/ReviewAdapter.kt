package com.kotlin.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.myapplication.databinding.ItemMoviePreviewBinding
import com.kotlin.myapplication.databinding.ItemMovieReviewBinding
import com.kotlin.myapplication.models.item.MovieItemModel
import com.kotlin.myapplication.models.response.Review


/**
 * Created by @erickrenata on 19/10/22.
 */

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieReviewBinding.inflate(inflater)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)
    }

    inner class ReviewViewHolder(private val binding: ItemMovieReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Review) {
            binding.apply {
                binding.item = item
                binding.executePendingBindings()
            }
        }
    }
}