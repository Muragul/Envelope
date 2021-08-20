package com.example.envelope.ui.start.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.envelope.databinding.FragmentCardBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.COMPLETE_TAG
import com.example.envelope.utils.binding.BindingFragment
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy

class CardFragment : BindingFragment<FragmentCardBinding>(FragmentCardBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            includeNav.apply {
                btnBack.setOnClickListener {
                    activity?.onBackPressed()
                }
                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }
                btnNext.setOnClickListener {
                    if (
                        checkCardNumber(etCardNumber.text.toString()) &&
                        checkFullName(etCardOwnerName.text.toString()) &&
                        checkDate(etCardExpireDate.text.toString()) &&
                        checkCvv(etCardCvv.text.toString())
                    ) (activity as StartActivity).changeFragment(
                        CompleteFragment(),
                        COMPLETE_TAG
                    ) else
                        Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
                }
            }
        }
        initViews()
    }

    private fun initViews() {
        MaskedTextChangedListener.installOn(
            binding.etCardNumber,
            "[0000] [0000] [0000] [0000]",
            arrayListOf("[0000] [0000] [0000] [0000]"),
            AffinityCalculationStrategy.WHOLE_STRING
        )
        MaskedTextChangedListener.installOn(
            binding.etCardExpireDate,
            "[00]{/}[00]",
            arrayListOf("[00]{/}[00]"),
            AffinityCalculationStrategy.WHOLE_STRING
        )
    }

    private fun checkCardNumber(cardNumber: String): Boolean = cardNumber.length == 19
    private fun checkFullName(fullName: String): Boolean = fullName.isNotEmpty()
    private fun checkDate(date: String): Boolean =
        if (date.length != 5) false
        else date.substring(0, date.indexOf("/")).toInt() < 13

    private fun checkCvv(cvv: String): Boolean = cvv.length == 3
}