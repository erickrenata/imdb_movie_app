package com.kotlin.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.myapplication.databinding.ItemMoviePreviewBinding
import com.kotlin.myapplication.models.item.MovieItemViewModel


/**
 * Created by @erickrenata on 03/04/22.
 */

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MovieItemViewModel>() {
        override fun areItemsTheSame(oldItem: MovieItemViewModel, newItem: MovieItemViewModel): Boolean {
            return oldItem.urlImage == newItem.urlImage
        }

        override fun areContentsTheSame(oldItem: MovieItemViewModel, newItem: MovieItemViewModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviePreviewBinding.inflate(inflater)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.bind(movie)
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let {
                    it(movie)
                }
            }
        }
    }

    private var onItemClickListener: ((MovieItemViewModel) -> Unit)? = null

    fun  setOnClickListener(listener: (MovieItemViewModel) -> Unit) {
        onItemClickListener = listener
    }

    inner class MovieViewHolder(private val binding: ItemMoviePreviewBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: MovieItemViewModel){
            binding.apply {
                binding.item = item
                binding.executePendingBindings()
            }
        }
    }
}