package com.example.envelope.ui.start.amount

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.example.envelope.databinding.FragmentAmountBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.ui.start.distribution.DistributionFragment
import com.example.envelope.utils.DISTRIBUTION_TAG
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.disable

class AmountFragment : BindingFragment<FragmentAmountBinding>(FragmentAmountBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            (activity as StartActivity).stepOnFocus(1)
            btnNext.disable()
            btnNext.setOnClickListener {
                (activity as StartActivity).showFragment(
                    DistributionFragment(),
                    DISTRIBUTION_TAG
                )
                (activity as StartActivity).stepOnCompleted(1)
            }
            etAmount.doAfterTextChanged {
                btnNext.isEnabled = !it.isNullOrBlank()
            }
        }
    }

}