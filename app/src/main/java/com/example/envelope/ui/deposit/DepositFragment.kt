package com.example.envelope.ui.deposit

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentDepositBinding
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.depositList

class DepositFragment : BindingFragment<FragmentDepositBinding>(FragmentDepositBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            val adapter = DepositAdapter()
            adapter.submitList(depositList)
            rvDeposit.adapter = adapter
        }
    }
}