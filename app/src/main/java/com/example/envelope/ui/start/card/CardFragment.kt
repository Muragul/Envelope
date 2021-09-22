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
import com.example.envelope.utils.MONEY_AMOUNT
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.*
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy

class CardFragment : BindingFragment<FragmentCardBinding>(FragmentCardBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        binding.run {
            btnNext.setOnClickListener {
                stepOnCompleted(3)
                goToCompletion()
            }
        }
    }

    private fun goToCompletion() {
        if (validateFields())
            showCompleteDialog()
        else {
            //todo implement handle error
            showIncorrectCardNumber()
            showIncorrectCvv()
            showIncorrectDate()
            showIncorrectFullName()
            Toast.makeText(context, R.string.fill_empty_fields, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showCompleteDialog() {
        val dialog = Dialog(binding.root.context)
        val dialogBinding = DistributionOnCompleteDialogBinding.inflate(layoutInflater)
        dialogBinding.tvWithdrawAmount.text = String.format(
            getString(R.string.finish_set_up_caution),
            MONEY_AMOUNT.toString()
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.cbCondition.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dialogBinding.btnNext.enable()
            } else {
                dialogBinding.btnNext.disable()
            }
        }
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
        return binding.etCardNumber.text.toString().length == 19
                && binding.etCardOwnerName.text.isNotEmpty() &&
                if (binding.etCardExpireDates.text.toString().length != 5) false
                else binding.etCardExpireDates.text.toString()
                    .substring(0, binding.etCardExpireDates.text.toString().indexOf("/"))
                    .toInt() < 13 &&
                        binding.etCardCvv.text.toString().length == 3
    }

    private fun initViews() {
        binding.run {
            firstStepDistribution.apply {
                tvStepTitle.text = getString(R.string.start_progress_first_step)
                tvStepNumber.text = "1"
            }
            secondStepDistribution.apply {
                tvStepTitle.text = getString(R.string.start_progress_second_step)
                tvStepNumber.text = "2"
            }
            thirdStepDistribution.apply {
                tvStepTitle.text = getString(R.string.start_progress_third_step)
                tvStepNumber.text = "3"
            }
        }
        stepOnCompleted(1)
        stepOnFocus()
        MaskedTextChangedListener.installOn(
            binding.etCardNumber,
            "[0000] [0000] [0000] [0000]",
            arrayListOf("[0000] [0000] [0000] [0000]"),
            AffinityCalculationStrategy.WHOLE_STRING
        )
        MaskedTextChangedListener.installOn(
            binding.etCardExpireDates,
            "[00]{/}[00]",
            arrayListOf("[00]{/}[00]"),
            AffinityCalculationStrategy.WHOLE_STRING
        )
    }


    private fun showIncorrectCardNumber() {
        binding.run {
            incorrectCardNumber.show()
            etCardNumber.showError()
        }
    }

    private fun showIncorrectCvv() {
        binding.run {
            incorrectCvvCode.show()
            etCardCvv.showError()
        }
    }

    private fun showIncorrectFullName() {
        binding.run {
            incorrectFullName.show()
            etCardOwnerName.showError()
        }
    }

    private fun showIncorrectDate() {
        binding.run {
            incorrectDate.show()
            etCardExpireDates.showError()
        }
    }


    private fun stepOnCompleted(step: Int) {
        if (step == 3) {
            binding.thirdStepDistribution.ivStepIcon.setStepDone()
            binding.thirdStepDistribution.tvStepNumber.hide()
        } else {
            binding.firstStepDistribution.ivStepIcon.setStepDone()
            binding.firstStepDistribution.tvStepNumber.hide()
            binding.firstStepDistribution.tvStepTitle.setStepActive()
            binding.secondStepDistribution.ivStepIcon.setStepDone()
            binding.secondStepDistribution.tvStepNumber.hide()
            binding.secondStepDistribution.tvStepTitle.setStepActive()
        }
    }

    private fun stepOnFocus() {
        binding.thirdStepDistribution.ivStepIcon.setStepActive()
        binding.thirdStepDistribution.tvStepNumber.setStepActive()
        binding.thirdStepDistribution.tvStepTitle.setStepActive()
    }

}