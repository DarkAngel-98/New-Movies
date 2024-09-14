package com.solo.category

import com.solo.common.network.domain.models.categories.Categories

interface OnCategoryClick {
    fun onClick(item: Categories)
}