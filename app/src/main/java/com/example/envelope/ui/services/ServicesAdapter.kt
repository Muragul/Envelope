package com.example.envelope.ui.services

import com.example.envelope.data.Services
import com.example.envelope.databinding.ServiceItemLayoutBinding
import com.example.envelope.utils.binding.BindingAdapter

class ServicesAdapter :
    BindingAdapter<Services, ServiceItemLayoutBinding>(ServiceItemLayoutBinding::inflate) {
    override fun bind(service: Services, binding: ServiceItemLayoutBinding) {
        binding.run {
            cvServiceTitle.text = service.title
        }
    }
}