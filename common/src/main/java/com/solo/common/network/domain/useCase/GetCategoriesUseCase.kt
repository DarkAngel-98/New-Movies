package com.solo.common.network.domain.useCase

import com.solo.common.base.BaseUseCaseNoParams
import com.solo.common.network.domain.models.CustomResult
import com.solo.common.network.domain.models.categories.Categories
import com.solo.common.network.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCategoriesUseCase @Inject constructor(private val mainRepository: MainRepository) :
    BaseUseCaseNoParams<CustomResult<List<Categories>>> {

    override suspend fun invoke(): Flow<CustomResult<List<Categories>>> =
        mainRepository.getAllCategories()
}