package com.example.movielist.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movielist.Client.ApiClient
import com.example.movielist.Modal.ResultsItem
import com.example.movielist.databinding.MovielistBinding

class MoviesAdapter : Adapter<MoviesAdapter.MovieHolder>() {

    lateinit var context: Context

    lateinit var Movie: List<ResultsItem>

    class MovieHolder(itemView: MovielistBinding) : ViewHolder(itemView.root) {

        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {

        context = parent.context
        var binding = MovielistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun getItemCount(): Int {

        return Movie.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.binding.apply {
            Movie.get(position).apply {

                txtTitel.text = originalTitle
                txtDescription.text = overview
                Glide.with(context).load(ApiClient.IMAGE_BASE_URL+posterPath).into(imgPoster)
            }
        }
    }

    fun setmovies(list: ArrayList<ResultsItem>) {
        this.Movie = list as List<ResultsItem>
    }
}