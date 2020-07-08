package com.usoof.movies.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchMovies(

    @Expose
    @SerializedName("title")
    val name: String,

    @Expose
    @SerializedName("original_title")
    val originalName: String,

    @Expose
    @SerializedName("genre_ids")
    val genreIds: List<Int>,

    @Expose
    @SerializedName("popularity")
    val popularity: Double,

    @Expose
    @SerializedName("vote_count")
    val voteCount: Int,

    @Expose
    @SerializedName("release_date")
    val releaseDate: String,

    @Expose
    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @Expose
    @SerializedName("original_language")
    val originalLanguage: String,

    @Expose
    @SerializedName("id")
    val id: Int,

    @Expose
    @SerializedName("vote_average")
    val voteAverage: Double,

    @Expose
    @SerializedName("overview")
    val overview: String,

    @Expose
    @SerializedName("poster_path")
    val posterPath: String?,

    @Expose
    @SerializedName("adult")
    val adult: Boolean,

    @Expose
    @SerializedName("video")
    val video: Boolean

)