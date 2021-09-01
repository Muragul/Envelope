package com.example.envelope.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.envelope.R
import com.example.envelope.databinding.ActivityStartBinding
import com.example.envelope.ui.main.MainActivity
import com.example.envelope.ui.start.amount.AmountFragment
import com.example.envelope.utils.binding.BindingActivity
import com.example.envelope.utils.extensions.hide

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
            showFragment(fragment = AmountFragment())
        }
    }

    fun showFragment(fragment: Fragment, tag: String? = null) {
        changeFragment(fragment = fragment, tag = tag, container = binding.container.id)
    }

    fun stepOnCompleted(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepAmount.ivStepIcon.background =
                    getDrawable(R.drawable.progress_bg_done)
                binding.firstStepAmount.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.firstStepAmount.tvStepNumber.hide()
            }
            2 -> {
                binding.secondStepDistribution.ivStepIcon.background =
                    getDrawable(R.drawable.progress_bg_done)
                binding.secondStepDistribution.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.secondStepDistribution.tvStepNumber.hide()
            }
            3 -> {
                binding.thirdStepCard.ivStepIcon.background =
                    getDrawable(R.drawable.progress_bg_done)
                binding.thirdStepCard.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.thirdStepCard.tvStepNumber.hide()
            }
        }
    }

    fun stepOnFocus(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepAmount.ivStepIcon.background = getDrawable(R.drawable.progress_bg)
                binding.firstStepAmount.tvStepNumber.setTextColor(resources.getColor(R.color.button_light_blue))
                binding.firstStepAmount.tvStepTitle.setTextColor(resources.getColor(R.color.button_light_blue))
            }
            2 -> {
                binding.secondStepDistribution.ivStepIcon.background =
                    getDrawable(R.drawable.progress_bg)
                binding.secondStepDistribution.tvStepNumber.setTextColor(resources.getColor(R.color.button_light_blue))
                binding.secondStepDistribution.tvStepTitle.setTextColor(resources.getColor(R.color.button_light_blue))
            }
            3 -> {
                binding.thirdStepCard.ivStepIcon.background =
                    getDrawable(R.drawable.progress_bg)
                binding.thirdStepCard.tvStepNumber.setTextColor(resources.getColor(R.color.button_light_blue))
                binding.thirdStepCard.tvStepTitle.setTextColor(resources.getColor(R.color.button_light_blue))
            }
        }
    }

    fun restart() {
        startActivity(Intent(this, StartActivity::class.java))
        finish()
    }

    fun completeSetup() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}