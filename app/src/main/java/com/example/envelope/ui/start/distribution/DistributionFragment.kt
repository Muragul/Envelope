package com.example.envelope.ui.start.distribution

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentDistributionBinding
import com.example.envelope.ui.ContainerActivity
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.ui.start.card.CardFragment
import com.example.envelope.utils.CARD_TAG
import com.example.envelope.utils.REQUEST_CODE
import com.example.envelope.utils.SCREEN
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.expensesList
import com.example.envelope.utils.extensions.check
import com.example.envelope.utils.extensions.enable
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.show
import com.example.envelope.utils.navigation.Screen

class DistributionFragment :
    BindingFragment<FragmentDistributionBinding>(FragmentDistributionBinding::inflate) {

//    private val startForResult = this.registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result: ActivityResult ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            val intent = result.data
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            val adapter = ExpensesAdapter()
            adapter.submitList(expensesList)
            rvServices.adapter = adapter
            includeNav.apply {
                //todo disable "Next" button when logic is ready
//                btnNext.disable()
                btnBack.setOnClickListener {
                    activity?.onBackPressed()
                }
                btnNext.setOnClickListener {
                    (activity as StartActivity).showFragment(
                        CardFragment(),
                        CARD_TAG
                    )
                }
                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }

                btnSaveExpenses.setOnClickListener {
                    ltExpensesContent.hide()
                    tvTitleExpenses.check()
                }

                btnSaveSavings.setOnClickListener {
                    ltSavingsContent.hide()
                    tvSavings.check()
                }
                btnSaveUnexpected.setOnClickListener {
                    ltUnexpectedContent.hide()
                    tvTitleUnexpected.check()
                    btnNext.enable()
                }

                btnAddService.setOnClickListener {
                    openServices()
                }

                ltExpenses.setOnClickListener {
                    //todo refactor: add toggle extension
                    if (ltExpensesContent.visibility == View.GONE)
                        ltExpensesContent.show()
                    else
                        ltExpensesContent.hide()
                }

                ltSavings.setOnClickListener {
                    //todo refactor: add toggle extension
                    if (ltSavingsContent.visibility == View.GONE)
                        ltSavingsContent.show()
                    else
                        ltSavingsContent.hide()
                }

                ltUnexpected.setOnClickListener {
                    //todo refactor: add toggle extension
                    if (ltUnexpectedContent.visibility == View.GONE)
                        ltUnexpectedContent.show()
                    else
                        ltUnexpectedContent.hide()
                }
            }
        }
    }

    private fun openServices() {
        val bundle = Bundle()
        bundle.putSerializable(SCREEN, Screen.SERVICES)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }
}