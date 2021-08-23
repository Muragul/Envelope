package com.example.envelope.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.envelope.R
import com.example.envelope.databinding.ActivityStartBinding
import com.example.envelope.ui.start.fragments.AmountFragment
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
            showFragment(fragment = AmountFragment())
        }
    }

    fun updateProgressBar(finishedStep: Int) {
    }

    fun showFragment(fragment: Fragment, tag: String? = null) {
        changeFragment(fragment = fragment, tag = tag, container = binding.container.id)
    }

    fun restart() {
        startActivity(Intent(this, StartActivity::class.java))
        finish()
    }

}