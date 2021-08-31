package com.example.envelope.utils.navigation

import android.os.Bundle
import com.example.envelope.ui.ContainerActivity
import com.example.envelope.ui.budget.BudgetFragment
import com.example.envelope.ui.deposit.DepositFragment
import com.example.envelope.ui.payment.all.PaymentAllFragment
import com.example.envelope.ui.payment.detail.PaymentDetailFragment
import com.example.envelope.ui.services.ServicesFragment

object FragmentNavigator {

    fun openFragment(
        activity: ContainerActivity,
        screen: Screen,
        data: Bundle? = null,
        tag: String? = null,
//        targetFragment: Fragment? = null,
//        requestCode: Int? = null
    ) {
        val fragment = when (screen) {
            Screen.EXPENSES -> {
                PaymentAllFragment.newInstance(bundle = data)
            }
            Screen.PAYMENT -> {
                PaymentDetailFragment.newInstance(bundle = data)
            }
            Screen.SERVICES -> {
                ServicesFragment.newInstance(bundle = data)
            }
            Screen.DEPOSITS -> {
                DepositFragment.newInstance(bundle = data)
            }
            Screen.BUDGET -> {
                BudgetFragment.newInstance(bundle = data)
            }
        }
        activity.showFragment(
            fragment = fragment,
            tag = tag
        )
    }

}