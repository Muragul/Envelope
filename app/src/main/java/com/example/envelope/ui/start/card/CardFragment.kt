package com.example.envelope.ui.start.card

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
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
        binding.incorrectCardNumber.show()
        binding.etCardNumber.setBackgroundResource(R.drawable.error_edit_text)
        binding.etCardNumber.setTextColor(resources.getColor(R.color.red))
    }

    private fun showIncorrectCvv() {
        binding.incorrectCvvCode.show()
        binding.etCardCvv.setBackgroundResource(R.drawable.error_edit_text)
        binding.etCardCvv.setTextColor(resources.getColor(R.color.red))
    }

    private fun showIncorrectFullName() {
        binding.incorrectFullName.show()
        binding.etCardOwnerName.setBackgroundResource(R.drawable.error_edit_text)
        binding.etCardOwnerName.setTextColor(resources.getColor(R.color.red))
    }

    private fun showIncorrectDate() {
        binding.incorrectDate.show()
        binding.etCardExpireDate.setBackgroundResource(R.drawable.error_edit_text)
        binding.etCardExpireDate.setTextColor(resources.getColor(R.color.red))
    }

}