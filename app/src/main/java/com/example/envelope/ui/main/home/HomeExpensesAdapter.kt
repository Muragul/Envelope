package com.example.envelope.ui.main.home

import com.example.envelope.R
import com.example.envelope.data.Expense
import com.example.envelope.databinding.ItemExpensesCardBinding
import com.example.envelope.utils.binding.BindingAdapter

class HomeExpensesAdapter(
    val itemClick: (id: Int) -> Unit
) :
    BindingAdapter<Expense, ItemExpensesCardBinding>(ItemExpensesCardBinding::inflate) {
    override fun bind(item: Expense, binding: ItemExpensesCardBinding) {
        binding.run {
            tvExpensesAmount.text = String.format(
                tvExpensesAmount.context.getString(R.string.total_price),
                item.totalSum.toString()
            )
            tvExpensesTitle.text = item.title
            btnPayExpenses.setOnClickListener {
                itemClick.invoke(item.id)
            }
        }
    }
}