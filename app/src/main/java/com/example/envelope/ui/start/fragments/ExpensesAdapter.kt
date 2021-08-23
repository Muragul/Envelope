package com.example.envelope.ui.start.fragments

import com.example.envelope.data.Expense
import com.example.envelope.databinding.ExpensesItemBinding
import com.example.envelope.utils.binding.BindingAdapter

class ExpensesAdapter : BindingAdapter<Expense, ExpensesItemBinding>(ExpensesItemBinding::inflate) {
    override fun bind(item: Expense, binding: ExpensesItemBinding) {
        binding.run {
            cvServiceTitle.text = item.name
            cvServicePrice.text = item.price.toString()
        }
    }
}