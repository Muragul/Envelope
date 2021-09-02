package com.example.envelope.ui.main.home

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentHomeBinding
import com.example.envelope.ui.ContainerActivity
import com.example.envelope.utils.*
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.navigation.Screen

class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            val adapter = HomeExpensesAdapter(
                itemClick = { id: Int ->
                    openPayment(id)
                }
            )
            adapter.submitList(expensesList)
            rvExpenses.adapter = adapter

            tvAllExpenses.setOnClickListener {
                openServices()
            }

            btnCreateDeposit.setOnClickListener {
                openDeposit()
            }

            btnBudget.ltRoot.setOnClickListener {
                openBudget()
            }
        }
    }

    private fun openServices() {
        val bundle = Bundle()
        bundle.putSerializable(SCREEN, Screen.EXPENSES)
        bundle.putString(SCREEN_TITLE, PAYMENT_ALL_FRAGMENT_TITLE)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

    private fun openBudget() {
        val bundle = Bundle()
        bundle.putString(SCREEN_TITLE, BUDGET_FRAGMENT_TITLE)
        bundle.putSerializable(SCREEN, Screen.BUDGET)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

    private fun openPayment(id: Int) {
        val bundle = Bundle()
        bundle.putString(SCREEN_TITLE, PAYMENT_DETAIL_FRAGMENT_TITLE)
        bundle.putSerializable(SCREEN, Screen.PAYMENT)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

    private fun openDeposit() {
        val bundle = Bundle()
        bundle.putString(SCREEN_TITLE, DEPOSIT_FRAGMENT_TITLE)
        bundle.putSerializable(SCREEN, Screen.DEPOSITS)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

}