package com.example.envelope.ui.start.fragments

import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import com.example.envelope.R
import com.example.envelope.databinding.FragmentDistributionBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.CARD_TAG
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.show
import com.example.envelope.utils.servicesList

class DistributionFragment :
    BindingFragment<FragmentDistributionBinding>(FragmentDistributionBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            val adapter = ExpensesAdapter()
            val servicesList = servicesList
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

    private fun initViews() {
        hideCheckMark(binding.tvTitleExpenses)
        hideCheckMark(binding.tvSavings)
        hideCheckMark(binding.tvTitleUnexpected)

    }


}