package com.example.envelope.ui.payment.detail

import android.os.Bundle
import android.view.View
import com.example.envelope.R
import com.example.envelope.databinding.FragmentPaymentDetailBinding
import com.example.envelope.utils.SCREEN_TITLE
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.cardsList
import com.example.envelope.utils.extensions.show

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

    private fun showIncorrectCardNumber() {
        binding.incorrectPhoneNumber.show()
        binding.etPhoneNumber.setBackgroundResource(R.drawable.error_edit_text)
        binding.etPhoneNumber.setTextColor(resources.getColor(R.color.red))
    }

    private fun showIncorrectSum() {
        binding.incorrectSum.show()
        binding.etSum.setBackgroundResource(R.drawable.error_edit_text)
        binding.etSum.setTextColor(resources.getColor(R.color.red))
    }

}