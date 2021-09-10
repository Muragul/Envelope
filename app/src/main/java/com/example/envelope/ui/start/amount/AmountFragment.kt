package com.example.envelope.ui.start.amount

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.example.envelope.R
import com.example.envelope.databinding.FragmentAmountBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.ui.start.distribution.DistributionFragment
import com.example.envelope.utils.DISTRIBUTION_TAG
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.disable
import com.example.envelope.utils.extensions.setStepActive

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
            btnNext.disable()
            etAmount.doAfterTextChanged {
                btnNext.isEnabled = !it.isNullOrBlank()
            }
            stepOnFocus()
        }
    }

    private fun stepOnFocus() {
        binding.firstStepAmount.ivStepIcon.setStepActive()
        binding.firstStepAmount.tvStepNumber.setStepActive()
        binding.firstStepAmount.tvStepTitle.setStepActive()
    }

}