package com.example.envelope.ui.start.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.envelope.databinding.FragmentDistributionBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.binding.BindingFragment

class DistributionFragment :
    BindingFragment<FragmentDistributionBinding>(FragmentDistributionBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            includeNav.apply {
                btnBack.setOnClickListener {
                    findNavController().navigateUp()
                }
                btnNext.setOnClickListener {
                    findNavController().navigate(DistributionFragmentDirections.actionToCard())
                }
                btnReturn.setOnClickListener {
                    startActivity(Intent(context, StartActivity::class.java))
                    activity?.finish()
                }
            }
        }
    }

}