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
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.show
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
        stepOnCompleted(2)
        stepOnFocus(3)
        stepOnCompleted(1)
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


    fun stepOnCompleted(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_done)
                binding.firstStepDistribution.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.firstStepDistribution.tvStepNumber.hide()
            }
            2 -> {
                binding.secondStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_done)
                binding.secondStepDistribution.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.secondStepDistribution.tvStepNumber.hide()
            }
            3 -> {
                binding.thirdStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_done)
                binding.thirdStepDistribution.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.thirdStepDistribution.tvStepNumber.hide()
            }
        }
    }

    fun stepOnFocus(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.progress_bg
                    )
                binding.firstStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
                binding.firstStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
            }
            2 -> {

                binding.secondStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg)
                binding.secondStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
                binding.secondStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )

            }
            3 -> {

                binding.thirdStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg)
                binding.thirdStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
                binding.thirdStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
            }
        }
    }

    fun makeStepDefault(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_default)
                binding.firstStepDistribution.ivStepIcon.setImageResource(0)
                binding.firstStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
                binding.firstStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
            }
            2 -> {
                binding.secondStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_default)
                binding.secondStepDistribution.ivStepIcon.setImageResource(0)
                binding.secondStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
                binding.secondStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
            }
            3 -> {
                binding.thirdStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_default)
                binding.thirdStepDistribution.ivStepIcon.setImageResource(0)
                binding.thirdStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
                binding.thirdStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
            }
        }
    }
}