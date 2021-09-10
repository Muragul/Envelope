package com.example.envelope.ui.start.amount

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.example.envelope.R
import com.example.envelope.databinding.FragmentAmountBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.ui.start.distribution.DistributionFragment
import com.example.envelope.utils.DISTRIBUTION_TAG
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.disable
import com.example.envelope.utils.extensions.hide

class AmountFragment : BindingFragment<FragmentAmountBinding>(FragmentAmountBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        binding.run {
            btnNext.setOnClickListener {
                (activity as StartActivity).showFragment(
                    DistributionFragment(),
                    DISTRIBUTION_TAG
                )
            }
        }
    }

    private fun initViews() {
        binding.run {
            firstStepAmount.apply {
                tvStepTitle.text = getString(R.string.start_progress_first_step)
                tvStepNumber.text = "1"
            }
            secondStepDistribution.apply {
                tvStepTitle.text = getString(R.string.start_progress_second_step)
                tvStepNumber.text = "2"
            }
            thirdStepCard.apply {
                tvStepTitle.text = getString(R.string.start_progress_third_step)
                tvStepNumber.text = "3"
            }
            //(activity as StartActivity).stepOnFocus(1)
            btnNext.disable()
            etAmount.doAfterTextChanged {
                btnNext.isEnabled = !it.isNullOrBlank()
            }
            stepOnFocus(1)
        }
    }


    fun stepOnCompleted(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepAmount.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_done)
                binding.firstStepAmount.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.firstStepAmount.tvStepNumber.hide()
            }
            2 -> {
                binding.secondStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_done)
                binding.secondStepDistribution.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.secondStepDistribution.tvStepNumber.hide()
            }
            3 -> {
                binding.thirdStepCard.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_done)
                binding.thirdStepCard.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.thirdStepCard.tvStepNumber.hide()
            }
        }
    }

    fun stepOnFocus(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepAmount.ivStepIcon.background =
                    ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.progress_bg
                    )
                binding.firstStepAmount.tvStepNumber.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
                binding.firstStepAmount.tvStepTitle.setTextColor(
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

                binding.thirdStepCard.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg)
                binding.thirdStepCard.tvStepNumber.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
                binding.thirdStepCard.tvStepTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
            }
        }
    }

    fun makeStepDefault(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepAmount.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_default)
                binding.firstStepAmount.ivStepIcon.setImageResource(0)
                binding.firstStepAmount.tvStepNumber.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
                binding.firstStepAmount.tvStepTitle.setTextColor(
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
                binding.thirdStepCard.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_default)
                binding.thirdStepCard.ivStepIcon.setImageResource(0)
                binding.thirdStepCard.tvStepNumber.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
                binding.thirdStepCard.tvStepTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
            }
        }
    }

}