package com.usoof.movies.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.usoof.movies.R
import com.usoof.movies.data.model.SearchMovies
import com.usoof.movies.utils.common.GlideHelper
import kotlinx.android.synthetic.main.item_movies.view.*
import javax.inject.Inject

class MovieAdapter(
    private val data: ArrayList<SearchMovies>
) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val context = parent.context
        val layoutId = R.layout.item_movies
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(layoutId, parent, shouldAttachToParentImmediately)

        return MoviesViewHolder(view)

    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun appendData(dataList: List<SearchMovies>) {
        val oldCount = itemCount
        this.data.addAll(dataList)
        val currentCount = itemCount
        if (oldCount == 0 && currentCount > 0)
            notifyDataSetChanged()
        else if (oldCount > 0 && currentCount > oldCount)
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

    fun updateList(list: List<SearchMovies>) {
        clearList()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun clearList() {
        data.clear()
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: SearchMovies) {
            itemView.tv_movieTitle.text = movie.name
            itemView.tv_movieVote.text = movie.voteAverage.toString()
            itemView.tv_movieOverview.text = movie.overview

            Glide
                .with(itemView.iv_moviePoster.context)
                .load(GlideHelper.getPosterUrl(movie.posterPath))
                .apply(RequestOptions.placeholderOf(R.drawable.ic_photo))
                .into(itemView.iv_moviePoster)
        }

    }

}