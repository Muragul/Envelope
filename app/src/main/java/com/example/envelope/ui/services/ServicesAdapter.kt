package com.example.envelope.ui.services

import com.example.envelope.data.Service
import com.example.envelope.databinding.ServiceItemLayoutBinding
import com.example.envelope.utils.binding.BindingAdapter

class ServicesAdapter :
    BindingAdapter<Service, ServiceItemLayoutBinding>(ServiceItemLayoutBinding::inflate) {
    override fun bind(item: Service, binding: ServiceItemLayoutBinding) {
        binding.run {
            cvServiceTitle.text = item.title
        }
    }
}