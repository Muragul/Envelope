package com.example.envelope.ui.start

import android.os.Bundle
import com.example.envelope.R
import com.example.envelope.databinding.ActivityStartBinding
import com.example.envelope.utils.binding.BindingActivity

class StartActivity : BindingActivity<ActivityStartBinding>(ActivityStartBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        }
    }

    fun updateProgressBar(finishedStep: Int) {
    }
}