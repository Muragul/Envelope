package com.example.envelope.ui.start.fragments

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentCardBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.COMPLETE_TAG
import com.example.envelope.utils.binding.BindingFragment

class CardFragment : BindingFragment<FragmentCardBinding>(FragmentCardBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            includeNav.apply {
                btnBack.setOnClickListener {
                    activity?.onBackPressed()
                }
                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }
                btnNext.setOnClickListener {
                    (activity as StartActivity).changeFragment(
                        CompleteFragment(),
                        COMPLETE_TAG
                    )
                }
            }
        }
    }
}