package com.solo.shared

import com.solo.common.network.domain.models.categories.Categories
import com.solo.common.network.domain.models.movie.Movie

data class SharedViewState(
    val categories: List<Categories>? = null,
    val moviesList: List<Movie>? = null,
    val category: String? = null
)
