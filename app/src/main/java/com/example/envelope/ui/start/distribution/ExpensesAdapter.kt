package com.example.envelope.ui.start.distribution

import com.example.envelope.R
import com.example.envelope.data.Expense
import com.example.envelope.databinding.ItemExpensesListBinding
import com.example.envelope.utils.binding.BindingAdapter

class ExpensesAdapter : BindingAdapter<Expense, ItemExpensesListBinding>(ItemExpensesListBinding::inflate) {
    override fun bind(item: Expense, binding: ItemExpensesListBinding) {
        binding.run {
            tvServiceTitle.text = item.title
            tvServicePrice.text = String.format(
                tvServicePrice.context.getString(R.string.total_price),
                item.totalSum.toString()
            )
        }
    }
}