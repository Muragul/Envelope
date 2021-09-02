package com.example.envelope.ui.payment.detail

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentPaymentDetailBinding
import com.example.envelope.utils.SCREEN_TITLE
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.cardsList

class PaymentDetailFragment :
    BindingFragment<FragmentPaymentDetailBinding>(FragmentPaymentDetailBinding::inflate) {

    companion object {
        fun newInstance(bundle: Bundle?): PaymentDetailFragment {
            val fragment = PaymentDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString(SCREEN_TITLE)
        binding.run {
            val adapter = CardAdapter(atvCards.context, cardsList)
            atvCards.setAdapter(adapter)
            toolbar.ivBack.setOnClickListener {
                activity?.onBackPressed()
            }
            toolbar.tvToolbarTitle.text = title.toString()
        }
    }
}