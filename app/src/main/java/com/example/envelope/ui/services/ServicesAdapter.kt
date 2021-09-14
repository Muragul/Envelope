package com.example.envelope.ui.services

import com.example.envelope.data.Service
import com.example.envelope.databinding.ItemServiceBinding
import com.example.envelope.utils.binding.BindingAdapter
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.loadUrl
import com.example.envelope.utils.extensions.show

class ServicesAdapter(val serviceItemClick: (Service) -> Unit) :
    BindingAdapter<Service, ItemServiceBinding>(ItemServiceBinding::inflate) {
    override fun bind(item: Service, binding: ItemServiceBinding) {
        binding.run {
            if (item.imageUrl.isNullOrEmpty())
                cvServiceIcon.hide()
            else {
                cvServiceIcon.show()
                ivServiceIcon.loadUrl(item.imageUrl)
            }
            tvServiceTitle.text = item.title
            binding.root.setOnClickListener {
                serviceItemClick(item)
            }
        }
    }
}