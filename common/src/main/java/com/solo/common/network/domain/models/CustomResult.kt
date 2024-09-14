package com.solo.common.network.domain.models

import androidx.annotation.Keep

@Keep
sealed class CustomResult<out T> {
    data class Success<out T>(val data: T) : CustomResult<T>()
    data class Failure<out E>(val error: E) : CustomResult<Nothing>()
}