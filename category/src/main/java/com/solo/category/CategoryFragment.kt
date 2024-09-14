package com.solo.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solo.category.databinding.FragmentCategoryBinding
import com.solo.common.network.domain.models.categories.Categories
import com.solo.shared.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment(), OnCategoryClick {

    private val viewModel: SharedViewModel by activityViewModels()
    private val categoriesAdapter by lazy { CategoryAdapter() }

    private lateinit var binding: FragmentCategoryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvCategories.layoutManager =
                GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            categoriesAdapter.setListener(this@CategoryFragment)
            rvCategories.adapter = categoriesAdapter
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED) {
                    viewModel.viewState.collectLatest { viewState ->
                        with(viewState) {
                            categories?.let {
                                categoriesAdapter.submitList(it)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onClick(item: Categories) {
        viewModel.apply {
            updateCategory(item.name)
            getMoviesByCategories(item.id)
        }
    }
}