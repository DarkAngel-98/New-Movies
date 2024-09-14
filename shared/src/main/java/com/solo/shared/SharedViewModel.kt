package com.solo.shared

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solo.common.network.domain.models.CustomResult
import com.solo.common.network.domain.useCase.GetCategoriesUseCase
import com.solo.common.network.domain.useCase.GetMovieByCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getMoviesByCategoryUseCase: GetMovieByCategoryUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(SharedViewState())
    val viewState: StateFlow<SharedViewState> = _viewState

    init {
        getAllCategories()
    }

    fun updateCategory(categoryName: String) {
        _viewState.update {
            it.copy(
                category = categoryName
            )
        }
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { result ->
                if (result is CustomResult.Success) {
                    _viewState.update {
                        it.copy(
                            categories = result.data,
                            category = result.data[0].name
                        )
                    }
                    delay(1000)
                    viewState.value.categories?.get(0)?.id?.let { getMoviesByCategories(it) }
                } else {
                    Log.d("dareCare", "getAllCategoriesError")
                }
            }
        }
    }

    fun getMoviesByCategories(categoryId: Int) {
        viewModelScope.launch {
            getMoviesByCategoryUseCase(GetMovieByCategoryUseCase.Params(categoryId)).collect { result ->
                if (result is CustomResult.Success) {
                    _viewState.update {
                        it.copy(
                            moviesList = result.data
                        )
                    }
                } else {
                    Log.d("dareCare", "getMoviesByCategories")
                }
            }
        }
    }

}