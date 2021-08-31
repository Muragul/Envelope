package com.example.envelope.ui.start.card

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.envelope.R
import com.example.envelope.databinding.FragmentCardBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.ui.start.complete.CompleteFragment
import com.example.envelope.utils.COMPLETE_TAG
import com.example.envelope.utils.binding.BindingFragment
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy

class CardFragment : BindingFragment<FragmentCardBinding>(FragmentCardBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            (activity as StartActivity).setStepFocus(3)
            includeNav.apply {
                btnBack.setOnClickListener {
                    activity?.onBackPressed()
                }
                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }
                btnNext.setOnClickListener {
                    (activity as StartActivity).setStep(3)
                    goToCompletion()
                }
            }
        }
        initViews()
    }

    private fun goToCompletion() {
        if (validateFields())
            (activity as StartActivity).showFragment(
                CompleteFragment(),
                COMPLETE_TAG
            ) else
            Toast.makeText(context, R.string.fill_empty_fields, Toast.LENGTH_SHORT).show()
    }

    private fun validateFields(): Boolean {
        //todo remove true when logic is ready
        return true || binding.etCardNumber.text.toString().length == 19
                && binding.etCardOwnerName.text.isNotEmpty() &&
                if (binding.etCardExpireDate.text.toString().length != 5) false
                else binding.etCardExpireDate.text.toString()
                    .substring(0, binding.etCardExpireDate.text.toString().indexOf("/"))
                    .toInt() < 13 &&
                        binding.etCardCvv.text.toString().length == 3
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
}