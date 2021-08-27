package com.example.envelope.ui.deposit

import com.example.envelope.data.Deposit
import com.example.envelope.databinding.ItemDepositBinding
import com.example.envelope.utils.binding.BindingAdapter
import com.example.envelope.utils.extensions.loadUrl

class DepositAdapter : BindingAdapter<Deposit, ItemDepositBinding>(ItemDepositBinding::inflate) {
    override fun bind(item: Deposit, binding: ItemDepositBinding) {
        binding.run {
            ivDepositImage.loadUrl(item.imageUrl)
            tvDepositTitle.text = item.title
            tvDepositDescription.text = item.description
        }
    }
}