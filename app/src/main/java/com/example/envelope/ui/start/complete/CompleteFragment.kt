package com.example.envelope.ui.start.complete

import android.os.Bundle
import android.view.View
import com.example.envelope.R
import com.example.envelope.databinding.FragmentCompleteBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.binding.BindingFragment

class CompleteFragment :
    BindingFragment<FragmentCompleteBinding>(FragmentCompleteBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            tvTotalAmount.text = String.format(
                getString(R.string.finish_set_up_caution),
                ""
            )

            includeNav.apply {
                btnBack.setOnClickListener {
                    activity?.onBackPressed()
                }
                btnNext.setOnClickListener {
                    (activity as StartActivity).completeSetup()
                }
                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }
            }
        }
    }

}