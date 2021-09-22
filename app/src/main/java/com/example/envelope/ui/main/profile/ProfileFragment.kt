package com.example.envelope.ui.main.profile

import android.os.Bundle
import android.view.View
import com.example.envelope.R
import com.example.envelope.databinding.FragmentProfileBinding
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.cardsList
import com.example.envelope.utils.defaultUser
import com.example.envelope.utils.extensions.getAgeSpelling

class ProfileFragment : BindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupListeners()
    }

    private fun setupListeners() {

    }

    private fun initViews() {
        binding.run {
            val adapter = UserCardsAdapter()
            val cards = cardsList
            adapter.submitList(cards)
            vpUserCards.adapter = adapter
            dotsIndicator.setViewPager2(vpUserCards)

            tvName.text = String.format(
                getString(R.string.name_surname),
                defaultUser.name,
                defaultUser.surname
            )
            tvAge.text = String.format(
                getString(R.string.user_age),
                defaultUser.age,
                defaultUser.age.getAgeSpelling()
            )
            tvGender.text = defaultUser.gender
        }
    }
}