package com.example.envelope.ui.main.profile

import android.os.Bundle
import android.view.View
import com.example.envelope.R
import com.example.envelope.databinding.FragmentProfileBinding
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.cardsList
import com.example.envelope.utils.defaultUser

class ProfileFragment : BindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            //todo check spelling
            tvAge.text = String.format(
                getString(R.string.user_age),
                defaultUser.age
            )
            tvGender.text = defaultUser.gender
        }
    }
}