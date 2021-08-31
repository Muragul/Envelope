package com.example.envelope.ui.services

import com.example.envelope.data.Service
import com.example.envelope.databinding.ItemServiceBinding
import com.example.envelope.utils.binding.BindingAdapter
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.show

class ServicesAdapter :
    BindingAdapter<Service, ItemServiceBinding>(ItemServiceBinding::inflate) {
    override fun bind(item: Service, binding: ItemServiceBinding) {
        binding.run {
            if (item.imageUrl.isNullOrEmpty())
                cvServiceIcon.hide()
            else cvServiceIcon.show()
            tvServiceTitle.text = item.title
        }
    }
}