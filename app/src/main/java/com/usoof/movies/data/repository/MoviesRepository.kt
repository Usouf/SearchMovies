package com.usoof.movies.data.repository

import com.usoof.movies.data.model.SearchMovies
import com.usoof.movies.data.remote.NetworkService
import io.reactivex.Observable
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val networkService: NetworkService
) {

    fun doMovieSearch(movieName: String, page: Int): Observable<List<SearchMovies>> =
        networkService.doMovieSearch(movieName, page)
            .map {
                it.results
            }

}