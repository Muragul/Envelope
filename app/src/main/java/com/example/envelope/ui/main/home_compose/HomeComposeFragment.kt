package com.example.envelope.ui.main.home_compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.envelope.R
import com.example.envelope.ui.ContainerActivity
import com.example.envelope.utils.REQUEST_CODE
import com.example.envelope.utils.SCREEN
import com.example.envelope.utils.SCREEN_TITLE
import com.example.envelope.utils.navigation.Screen

class HomeComposeFragment : Fragment() {

    @ExperimentalFoundationApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposePage(
                    onServiceClicked = { openServices() },
                    onPaymentClicked = { openPayment() },
                    onBudgetClicked = { openBudget() },
                    onDepositClicked = { openDeposit() }
                )
            }
        }
    }

    private fun openServices() {
        val bundle = Bundle()
        bundle.putSerializable(SCREEN, Screen.EXPENSES)
        bundle.putString(SCREEN_TITLE, getString(R.string.obligatory_expenses))
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

    private fun openBudget() {
        val bundle = Bundle()
        bundle.putString(SCREEN_TITLE, getString(R.string.envelops))
        bundle.putSerializable(SCREEN, Screen.BUDGET)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

    private fun openPayment() {
        val bundle = Bundle()
        bundle.putString(SCREEN_TITLE, getString(R.string.payment_detail_fragment_title))
        bundle.putSerializable(SCREEN, Screen.PAYMENT)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

    private fun openDeposit() {
        val bundle = Bundle()
        bundle.putString(SCREEN_TITLE, getString(R.string.deposit_fragment_title))
        bundle.putSerializable(SCREEN, Screen.DEPOSITS)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

}