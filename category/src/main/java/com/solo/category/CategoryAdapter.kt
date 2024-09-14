package com.solo.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solo.category.databinding.CategoryItemRowBinding
import com.solo.common.network.domain.models.categories.Categories
import javax.inject.Inject

class CategoryAdapter @Inject constructor() :
    ListAdapter<Categories, RecyclerView.ViewHolder>(
        fileItemDiffCallback
    ) {
    private var clickListener: OnCategoryClick? = null
    fun setListener(categoryListener: OnCategoryClick) {
        clickListener = categoryListener
    }

    companion object {
        val fileItemDiffCallback = object : DiffUtil.ItemCallback<Categories>() {
            override fun areItemsTheSame(
                oldItem: Categories,
                newItem: Categories
            ) = oldItem.id == newItem.id && oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: Categories,
                newItem: Categories
            ) =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as CategoryViewHolder).bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CategoryViewHolder(
            CategoryItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    inner class CategoryViewHolder(private val binding: CategoryItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(category: Categories) {
            binding.apply {
                tvCategoryName.text = category.name
                binding.root.setOnClickListener {
                    clickListener?.onClick(category)
                }
            }
        }
    }
}