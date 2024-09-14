package com.solo.common.network.domain.repository

import com.solo.common.network.domain.models.CustomResult
import com.solo.common.network.domain.models.categories.Categories
import com.solo.common.network.domain.models.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getAllCategories(): Flow<CustomResult<List<Categories>>>
    suspend fun getMoviesByCategory(category: Int): Flow<CustomResult<List<Movie>>>
}