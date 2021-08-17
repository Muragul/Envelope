package com.example.envelope.ui.start.fragments

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentDistributionBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.CARD_TAG
import com.example.envelope.utils.binding.BindingFragment

class DistributionFragment :
    BindingFragment<FragmentDistributionBinding>(FragmentDistributionBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            includeNav.apply {
                btnBack.setOnClickListener {
                    activity?.onBackPressed()
                }
                btnNext.setOnClickListener {
                    (activity as StartActivity).changeFragment(
                        CardFragment(),
                        CARD_TAG
                    )
                }
                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }
            }
        }
    }

}