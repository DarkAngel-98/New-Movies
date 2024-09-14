package com.solo.common.network.responseModels

import com.google.gson.annotations.SerializedName
import com.solo.common.network.domain.models.movie.Movie

data class MoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
