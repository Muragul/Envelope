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
import com.example.envelope.utils.extensions.hide

class AmountFragment : BindingFragment<FragmentAmountBinding>(FragmentAmountBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {

            includeNav.apply {
                btnBack.hide()
                btnNext.disable()

                btnNext.setOnClickListener {
                    (activity as StartActivity).showFragment(
                        DistributionFragment(),
                        DISTRIBUTION_TAG
                    )
                }

                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }
            }

            etAmount.doAfterTextChanged {
                includeNav.btnNext.isEnabled = !it.isNullOrBlank()
            }

        }
    }

}