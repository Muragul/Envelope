package com.example.envelope.ui.start.card

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
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
        validateFields()
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

    private fun validateFields() {
        binding.run {
            val check = booleanArrayOf(
                checkCardNumber(etCardNumber.text.toString()),
                checkFullName(etCardOwnerName.text.toString()),
                checkDate(etCardExpireDates.text.toString()),
                checkCvv(etCardCvv.text.toString())
            )
            if (!check.contains(false)) {
                showCompleteDialog()
            }
            for (i in check.indices) {
                when (i) {
                    0 -> {
                        if (!check[i]) {
                            etCardNumber.setText("")
                            showIncorrectCardNumber()
                        } else {
                            hideIncorrectCardNumber()
                        }
                    }
                    1 -> {
                        if (!check[i]) {
                            etCardOwnerName.setText("")
                            showIncorrectFullName()
                        } else {
                            hideIncorrectFullName()
                        }
                    }
                    2 -> {
                        if (!check[i]) {
                            etCardExpireDates.setText("")
                            showIncorrectDate()
                        } else {
                            hideIncorrectDate()
                        }
                    }
                    3 -> {
                        if (!check[i]) {
                            etCardCvv.setText("")
                            showIncorrectCvv()
                        } else {
                            hideIncorrectCvv()
                        }
                    }
                }
            }
        }
    }

    private fun hideIncorrectCvv() {
        binding.run {
            incorrectCvvCode.hide()
            etCardCvv.hideError()
        }
    }

    private fun hideIncorrectDate() {
        binding.run {
            incorrectDate.hide()
            etCardExpireDates.hideError()
        }
    }

    private fun hideIncorrectFullName() {
        binding.run {
            incorrectFullName.hide()
            etCardOwnerName.hideError()
        }
    }

    private fun hideIncorrectCardNumber() {
        binding.run {
            incorrectCardNumber.hide()
            etCardNumber.hideError()
        }
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

    private fun checkCardNumber(cardNumber: String): Boolean = cardNumber.length == 19
    private fun checkFullName(fullName: String): Boolean = fullName.isNotEmpty()
    private fun checkDate(date: String): Boolean =
        if (date.length != 5) false
        else date.substring(0, date.indexOf("/")).toInt() < 13 && date.substring(date.indexOf("/")+1)
            .toInt() < 32

    private fun checkCvv(cvv: String): Boolean = cvv.length == 3

}