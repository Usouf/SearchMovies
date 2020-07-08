package com.usoof.movies.utils.common

object GlideHelper {

    fun getPosterUrl(endpoint: String?): String =
        "${Constants.IMAGE_BASE_URL}${Constants.DEFAULT_POSTER_SIZE}$endpoint"

}