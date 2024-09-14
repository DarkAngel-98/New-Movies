package com.solo.common.network.data.repositoryImpl

import com.solo.common.network.ApiNetworkService
import com.solo.common.network.domain.models.CustomResult
import com.solo.common.network.domain.models.categories.Categories
import com.solo.common.network.domain.models.movie.Movie
import com.solo.common.network.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiNetworkService: ApiNetworkService) :
    MainRepository {

    override suspend fun getAllCategories(): Flow<CustomResult<List<Categories>>> = flow {
        try {
            val response = apiNetworkService.getAllAvailableCategories()
            emit(
                CustomResult.Success(
                    response.genres
                )
            )

        } catch (e: Exception) {
            emit(CustomResult.Failure(e))
        }
    }

    override suspend fun getMoviesByCategory(category: Int): Flow<CustomResult<List<Movie>>> =
        flow {
            try {
                val response = apiNetworkService.searchByGenre(category)
                emit(
                    CustomResult.Success(
                        response.results
                    )
                )

            } catch (e: Exception) {
                emit(CustomResult.Failure(e))
            }
        }
}