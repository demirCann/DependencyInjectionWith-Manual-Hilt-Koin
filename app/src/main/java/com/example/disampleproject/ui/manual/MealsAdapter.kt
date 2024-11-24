package com.example.disampleproject.ui.manual

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.disampleproject.data.model.Meal
import com.example.disampleproject.databinding.MealItemLayoutBinding

class MealsAdapter : ListAdapter<Meal, MealsAdapter.MealTypeViewHolder>(MealItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealTypeViewHolder {
        val binding =
            MealItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealTypeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MealTypeViewHolder(private val binding: MealItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            binding.apply {
                mealTitle.text = meal.title
                mealImage.load(meal.image)
            }
        }
    }

    class MealItemDiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }
}