package com.example.envelope.ui.payment.all

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentPaymentAllBinding
import com.example.envelope.ui.start.distribution.ExpensesAdapter
import com.example.envelope.utils.SCREEN_TITLE
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.expensesList

class PaymentAllFragment :
    BindingFragment<FragmentPaymentAllBinding>(FragmentPaymentAllBinding::inflate) {

    companion object {
        fun newInstance(bundle: Bundle?): PaymentAllFragment {
            val fragment = PaymentAllFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString(SCREEN_TITLE)
        binding.run {
            val adapter = ExpensesAdapter()
            adapter.submitList(expensesList)
            rvAllPayments.adapter = adapter
            toolbar.ivBack.setOnClickListener {
                activity?.onBackPressed()
            }
            toolbar.tvToolbarTitle.text = title.toString()
        }
    }
}