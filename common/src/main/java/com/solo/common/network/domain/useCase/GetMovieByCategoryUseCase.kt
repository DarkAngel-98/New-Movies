package com.solo.common.network.domain.useCase

import com.solo.common.base.BaseUseCase
import com.solo.common.network.domain.models.CustomResult
import com.solo.common.network.domain.models.categories.Categories
import com.solo.common.network.domain.models.movie.Movie
import com.solo.common.network.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMovieByCategoryUseCase @Inject constructor(private val mainRepository: MainRepository) :
    BaseUseCase<CustomResult<List<Movie>>, GetMovieByCategoryUseCase.Params> {

    override suspend fun invoke(params: Params): Flow<CustomResult<List<Movie>>> =
        mainRepository.getMoviesByCategory(params.categoryId)


    data class Params(val categoryId: Int)
}