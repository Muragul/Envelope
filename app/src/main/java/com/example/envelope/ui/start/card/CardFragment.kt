package com.example.envelope.ui.start.card

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.envelope.R
import com.example.envelope.databinding.DistributionOnCompleteDialogBinding
import com.example.envelope.databinding.FragmentCardBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.show
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy

class CardFragment : BindingFragment<FragmentCardBinding>(FragmentCardBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            (activity as StartActivity).stepOnFocus(3)
            includeNav.apply {
                btnBack.setOnClickListener {
                    (activity as StartActivity).stepOnFocus(2)
                    (activity as StartActivity).makeStepDefault(3)
                    activity?.onBackPressed()
                }
                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }
                btnNext.setOnClickListener {
                    (activity as StartActivity).stepOnCompleted(3)
                    goToCompletion()
                }
            }
        }
        initViews()
    }

    private fun goToCompletion() {
        if (validateFields())
            showCompleteDialog()
        else
            Toast.makeText(context, R.string.fill_empty_fields, Toast.LENGTH_SHORT).show()
    }

    private fun showCompleteDialog() {
        val dialog = Dialog(binding.root.context)
        val dialogBinding = DistributionOnCompleteDialogBinding.inflate(layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.btnNext.setOnClickListener {
            (activity as StartActivity).completeSetup()
        }
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
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


    private fun showIncorrectCardNumber() {
        binding.run {
            incorrectCardNumber.show()
            etCardNumber.setBackgroundResource(R.drawable.error_edit_text)
            etCardNumber.setTextColor(
                ContextCompat.getColor(etCardNumber.context, R.color.red)
            )
        }
    }

    private fun showIncorrectCvv() {
        binding.run {
            incorrectCvvCode.show()
            etCardCvv.setBackgroundResource(R.drawable.error_edit_text)
            etCardCvv.setTextColor(
                ContextCompat.getColor(etCardCvv.context, R.color.red)
            )
        }
    }

    private fun showIncorrectFullName() {
        binding.run {
            incorrectFullName.show()
            etCardOwnerName.setBackgroundResource(R.drawable.error_edit_text)
            etCardOwnerName.setTextColor(
                ContextCompat.getColor(etCardOwnerName.context, R.color.red)
            )
        }
    }

    private fun showIncorrectDate() {
        binding.run {
            incorrectDate.show()
            etCardExpireDate.setBackgroundResource(R.drawable.error_edit_text)
            etCardExpireDate.setTextColor(
                ContextCompat.getColor(etCardExpireDate.context, R.color.red)
            )
        }
    }

}