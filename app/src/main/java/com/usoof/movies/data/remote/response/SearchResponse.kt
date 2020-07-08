package com.usoof.movies.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.usoof.movies.data.model.SearchMovies

data class SearchResponse(

    @Expose
    @SerializedName("page")
    val page: Int,

    @Expose
    @SerializedName("results")
    val results: List<SearchMovies>,

    @Expose
    @SerializedName("total_results")
    val totalResults: Int,

    @Expose
    @SerializedName("total_pages")
    val totalPages: Int

)