package com.example.envelope.ui.payment.detail

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentPaymentDetailBinding
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.cardsList

class PaymentDetailFragment :
    BindingFragment<FragmentPaymentDetailBinding>(FragmentPaymentDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            val adapter = CardAdapter(atvCards.context, cardsList)
            atvCards.setAdapter(adapter)
        }
    }
}