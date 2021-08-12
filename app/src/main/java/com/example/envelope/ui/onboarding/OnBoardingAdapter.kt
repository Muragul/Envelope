package com.example.envelope.ui.onboarding

import com.example.envelope.data.OnBoarding
import com.example.envelope.databinding.ItemOnboardingBinding
import com.example.envelope.utils.binding.BindingAdapter
import com.example.envelope.utils.extensions.loadUrl

class OnBoardingAdapter :
    BindingAdapter<OnBoarding, ItemOnboardingBinding>(ItemOnboardingBinding::inflate) {

    override fun bind(item: OnBoarding, binding: ItemOnboardingBinding) {
        binding.run {
            ivImage.loadUrl(item.imageUrl)
            tvText.text = item.text
        }
    }
}