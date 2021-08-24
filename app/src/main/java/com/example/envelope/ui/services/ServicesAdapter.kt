package com.example.envelope.ui.services

import com.example.envelope.data.Service
import com.example.envelope.databinding.ItemServiceBinding
import com.example.envelope.utils.binding.BindingAdapter

class ServicesAdapter :
    BindingAdapter<Service, ItemServiceBinding>(ItemServiceBinding::inflate) {
    override fun bind(item: Service, binding: ItemServiceBinding) {
        binding.run {
            tvServiceTitle.text = item.title
        }
    }
}