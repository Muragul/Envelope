package com.example.envelope.ui.payment.detail

import android.os.Bundle
import android.view.View
import com.example.envelope.R
import com.example.envelope.databinding.FragmentPaymentDetailBinding
import com.example.envelope.utils.MONEY_AMOUNT
import com.example.envelope.utils.SCREEN_TITLE
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.cardsList
import com.example.envelope.utils.extensions.loadUrl
import com.example.envelope.utils.extensions.show
import com.example.envelope.utils.extensions.showError
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy

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
            val newAdapter =
                CardAdapter(atvCards.context, cardsList) { number, imageUrl ->
                    changeCard(
                        number,
                        imageUrl
                    )
                }
            MaskedTextChangedListener.installOn(
                etPhoneNumber,
                "[0] [000] [000] [00] [00]",
                arrayListOf("[0] [000] [000] [00] [00]"),
                AffinityCalculationStrategy.WHOLE_STRING
            )
            atvCards.setAdapter(newAdapter)
            toolbar.tvToolbarTitle.text = title.toString()
            btnPay.text =
                String.format(getString(R.string.service_payment), MONEY_AMOUNT.toString())
            ltChooseCard.setOnClickListener {
                atvCards.showDropDown()
            }
        }
    }

    private fun changeCard(number: String, imageUrl: String) {
        //todo refactor with proper view
        binding.run {
            atvCards.setText(number, false)
            ivCardIcon.loadUrl(imageUrl)
            atvCards.dismissDropDown()
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