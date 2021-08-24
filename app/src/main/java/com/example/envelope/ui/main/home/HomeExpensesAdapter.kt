package com.example.envelope.ui.main.home

import com.example.envelope.R
import com.example.envelope.data.Expense
import com.example.envelope.databinding.HomeExpensesItemBinding
import com.example.envelope.utils.binding.BindingAdapter

class HomeExpensesAdapter :
    BindingAdapter<Expense, HomeExpensesItemBinding>(HomeExpensesItemBinding::inflate) {
    override fun bind(item: Expense, binding: HomeExpensesItemBinding) {
        binding.run {
            binding.tvExpensesAmount.text = String.format(
                tvExpensesAmount.context.getString(R.string.total_price),
                item.totalSum.toString()
            )
            binding.tvExpensesTitle.text = item.title
        }
    }
}