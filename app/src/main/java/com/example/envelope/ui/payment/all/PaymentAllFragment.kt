package com.example.envelope.ui.payment.all

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentPaymentAllBinding
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.expensesList

class PaymentAllFragment :
    BindingFragment<FragmentPaymentAllBinding>(FragmentPaymentAllBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            val adapter = PaymentAllAdapter()
            adapter.submitList(expensesList)
            rvAllPayments.adapter = adapter
        }
    }
}