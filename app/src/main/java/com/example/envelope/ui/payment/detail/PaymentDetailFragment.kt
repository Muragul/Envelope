package com.example.envelope.ui.payment.detail

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.envelope.databinding.FragmentPaymentDetailBinding
import com.example.envelope.utils.SCREEN_TITLE
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.cardsList
import com.example.envelope.utils.extensions.show
import com.example.envelope.utils.extensions.showError

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
        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        binding.run {
            toolbar.ivBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun initViews() {
        binding.run {
            val title = arguments?.getString(SCREEN_TITLE)
            val adapter = ArrayAdapter(
                atvCards.context,
                android.R.layout.simple_spinner_dropdown_item,
                cardsList.map { item -> item.number }
            )
            atvCards.setAdapter(adapter)
            toolbar.tvToolbarTitle.text = title.toString()
        }
    }

    private fun showIncorrectCardNumber() {
        binding.run {
            incorrectPhoneNumber.show()
            etPhoneNumber.showError()
        }
    }

    private fun showIncorrectSum() {
        binding.run {
            incorrectSum.show()
            etSum.showError()
        }
    }

}