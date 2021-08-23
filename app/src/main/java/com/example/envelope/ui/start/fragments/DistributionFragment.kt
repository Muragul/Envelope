package com.example.envelope.ui.start.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.envelope.R
import com.example.envelope.databinding.FragmentDistributionBinding
import com.example.envelope.ui.services.ServicesActivity
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.CARD_TAG
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.expensesList
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.show

class DistributionFragment :
    BindingFragment<FragmentDistributionBinding>(FragmentDistributionBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            val adapter = ExpensesAdapter()
            val servicesList = expensesList
            adapter.submitList(servicesList)
            rvServices.adapter = adapter
            includeNav.apply {
                btnNext.isEnabled = false
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
                    showCheckMark(tvTitleExpenses)
                    ltSavingsContent.show()
                }

                btnSaveSavings.setOnClickListener {
                    ltSavingsContent.hide()
                    showCheckMark(tvSavings)
                    ltUnexpectedContent.show()
                }
                btnSaveUnexpected.setOnClickListener {
                    ltUnexpectedContent.hide()
                    showCheckMark(tvTitleUnexpected)
                    btnNext.isEnabled = true
                }

                btnAddService.setOnClickListener {
                    startForResult.launch(
                        Intent(
                            requireContext(),
                            ServicesActivity::class.java
                        )
                    )
                }
            }
        }
        initViews()
    }

    private fun showCheckMark(tv: CheckedTextView) {
        tv.setCheckMarkDrawable(R.drawable.ic_baseline_check_24)
    }

    private fun hideCheckMark(tv: CheckedTextView) {
        tv.checkMarkDrawable = null
    }

    val startForResult =
        this.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                
            }
        }

    private fun initViews() {
        hideCheckMark(binding.tvTitleExpenses)
        hideCheckMark(binding.tvSavings)
        hideCheckMark(binding.tvTitleUnexpected)

    }


}