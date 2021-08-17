package com.example.envelope.ui.start.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.envelope.databinding.FragmentCardBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.binding.BindingFragment

class CardFragment : BindingFragment<FragmentCardBinding>(FragmentCardBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            includeNav.apply {
                btnBack.setOnClickListener {
                    findNavController().navigateUp()
                }
                btnReturn.setOnClickListener {
                    startActivity(Intent(context, StartActivity::class.java))
                    activity?.finish()
                }
                btnNext.setOnClickListener {
                    findNavController().navigate(CardFragmentDirections.actionToFinish())
                }
            }
        }
    }
}