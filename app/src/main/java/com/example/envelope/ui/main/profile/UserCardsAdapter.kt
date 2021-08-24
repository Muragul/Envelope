package com.example.envelope.ui.main.profile

import com.example.envelope.data.Card
import com.example.envelope.databinding.ItemUserCardBinding
import com.example.envelope.utils.binding.BindingAdapter
import com.example.envelope.utils.extensions.loadUrl

class UserCardsAdapter : BindingAdapter<Card, ItemUserCardBinding>(ItemUserCardBinding::inflate) {

    override fun bind(item: Card, binding: ItemUserCardBinding) {
        binding.run {
            ivCardImage.loadUrl(item.imageUrl)
            tvExpireDate.text = item.expireDate
            tvType.text = item.type
            tvNumber.text = item.number
        }
    }
}