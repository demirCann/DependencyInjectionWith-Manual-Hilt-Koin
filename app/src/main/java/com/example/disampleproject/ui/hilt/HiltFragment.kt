package com.example.disampleproject.ui.hilt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.disampleproject.databinding.FragmentManualBinding
import com.example.disampleproject.ui.manual.MealsAdapter
import com.example.disampleproject.util.Constants.DI_WITH_HILT
import com.example.disampleproject.util.Constants.MAIN_COURSE
import com.example.disampleproject.util.LayoutUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HiltFragment : Fragment() {

    private var _binding: FragmentManualBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HiltViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManualBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.title = DI_WITH_HILT

        LayoutUtil.setLayoutManager(
            this.context,
            binding.recyclerView,
            resources.configuration.orientation
        )

        viewModel.fetchMeals(MAIN_COURSE, 10)

        val mealsAdapter = MealsAdapter()
        binding.recyclerView.adapter = mealsAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mealState.collect {
                    mealsAdapter.submitList(it?.results)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}