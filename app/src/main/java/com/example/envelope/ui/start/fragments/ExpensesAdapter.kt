package com.example.envelope.ui.start.fragments

import com.example.envelope.data.Service
import com.example.envelope.databinding.ExpensesItemBinding
import com.example.envelope.utils.binding.BindingAdapter

class ExpensesAdapter : BindingAdapter<Service, ExpensesItemBinding>(ExpensesItemBinding::inflate) {
    override fun bind(item: Service, binding: ExpensesItemBinding) {
        binding.run {
            cvServiceTitle.text = item.name
            cvServicePrice.text = item.price.toString()
        }
    }
}