package com.usoof.movies.data.remote

import com.usoof.movies.data.remote.response.SearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.SEARCH_MOVIES)
    fun doMovieSearch(
        @Query("query") movieName: String,
        @Query("page") page: Int,
        @Query(Networking.QUERY_API_KEY) apiKey: String = Networking.API_KEY
    ): Observable<SearchResponse>

}