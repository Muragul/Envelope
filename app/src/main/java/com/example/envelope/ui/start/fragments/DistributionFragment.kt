package com.example.envelope.ui.start.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.envelope.databinding.FragmentDistributionBinding
import com.example.envelope.ui.services.ServicesActivity
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.CARD_TAG
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.expensesList
import com.example.envelope.utils.extensions.disable
import com.example.envelope.utils.extensions.enable
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.showCheckMark

class DistributionFragment :
    BindingFragment<FragmentDistributionBinding>(FragmentDistributionBinding::inflate) {

    private val startForResult =
        this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            val adapter = ExpensesAdapter()
            adapter.submitList(expensesList)
            rvServices.adapter = adapter
            includeNav.apply {
                btnNext.disable()
                btnBack.setOnClickListener {
                    activity?.onBackPressed()
                }
                btnNext.setOnClickListener {
                    (activity as StartActivity).changeFragment(
                        CardFragment(),
                        CARD_TAG
                    )
                }
                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }

                btnSaveObligatory.setOnClickListener {
                    ltExpensesContent.hide()
                    tvTitleExpenses.showCheckMark()
                }

                btnSaveSavings.setOnClickListener {
                    ltSavingsContent.hide()
                    tvSavings.showCheckMark()
                }
                btnSaveUnexpected.setOnClickListener {
                    ltUnexpectedContent.hide()
                    tvTitleUnexpected.showCheckMark()
                    btnNext.enable()
                }

                btnAddService.setOnClickListener {
                    startForResult.launch(
                        Intent(
                            context,
                            ServicesActivity::class.java
                        )
                    )
                }
            }
        }
    }
}