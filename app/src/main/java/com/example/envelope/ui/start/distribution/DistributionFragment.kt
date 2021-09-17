package com.example.envelope.ui.start.distribution

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.envelope.R
import com.example.envelope.databinding.FragmentDistributionBinding
import com.example.envelope.ui.ContainerActivity
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.ui.start.card.CardFragment
import com.example.envelope.utils.*
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.*
import com.example.envelope.utils.navigation.Screen
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

class DistributionFragment :
    BindingFragment<FragmentDistributionBinding>(FragmentDistributionBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        binding.run {
            btnNext.setOnClickListener {
                (activity as StartActivity).showFragment(
                    CardFragment(),
                    CARD_TAG
                )
            }

            btnSaveExpenses.setOnClickListener {
                ltExpensesContent.hide()
                tvTitleExpenses.check()
                ltExpenses.setDarkBackground()
                tvTitleExpenses.setWhiteText()
                vDividerExpenses.show()
            }

            btnSaveSavings.setOnClickListener {
                ltSavingsContent.hide()
                tvSavings.check()
                ltSavings.setDarkBackground()
                tvSavings.setWhiteText()
                vDividerSavings.show()
            }
            btnSaveUnexpected.setOnClickListener {
                ltUnexpectedContent.hide()
                tvTitleUnexpected.check()
                btnNext.enable()
                ltUnexpected.setDarkBackground()
                tvTitleUnexpected.setWhiteText()
            }

            btnAddService.setOnClickListener {
                openServices()
            }
            ltExpenses.setOnClickListener {
                //todo refactor: add toggle extension
                if (ltExpensesContent.visibility == View.GONE) {
                    ltExpenses.setWhiteBackground()
                    tvTitleExpenses.setDarkText()
                    vDividerExpenses.hide()
                    ltExpensesContent.show()
                    ltExpensesContent.startDistributionAnimation()
                } else {
                    ltExpenses.setDarkBackground()
                    tvTitleExpenses.setWhiteText()
                    ltExpensesContent.hide()
                    vDividerExpenses.show()
                }
            }

            ltSavings.setOnClickListener {
                //todo refactor: add toggle extension
                if (ltSavingsContent.visibility == View.GONE) {
                    ltSavings.setWhiteBackground()
                    tvSavings.setDarkText()
                    ltSavingsContent.show()
                    vDividerSavings.hide()
                    ltSavingsContent.startDistributionAnimation()
                } else {
                    ltSavings.setDarkBackground()
                    tvSavings.setWhiteText()
                    ltSavingsContent.hide()
                    vDividerSavings.show()
                }
            }

            ltUnexpected.setOnClickListener {
                //todo refactor: add toggle extension
                if (ltUnexpectedContent.visibility == View.GONE) {
                    ltUnexpected.setWhiteBackground()
                    tvTitleUnexpected.setDarkText()
                    ltUnexpectedContent.show()
                    ltUnexpectedContent.startDistributionAnimation()
                } else {
                    ltUnexpected.setDarkBackground()
                    tvTitleUnexpected.setWhiteText()
                    ltUnexpectedContent.hide()
                }
            }

            sbSavingsPercent.onSeekChangeListener = object : OnSeekChangeListener {
                override fun onSeeking(p0: SeekParams?) {
                    if (p0 != null) {
                        val amount = (p0.progressFloat * MONEY_AMOUNT) / 100
                        if (amount == 0F) {
                            etSavingsAmount.setText("")
                        } else {
                            etSavingsAmount.setText(amount.toInt().toString())
                        }
                        etSavingsAmount.setSelection(etSavingsAmount.length())
                    }
                }

                override fun onStartTrackingTouch(p0: IndicatorSeekBar?) {

                }

                override fun onStopTrackingTouch(p0: IndicatorSeekBar?) {

                }

            }

            etSavingsAmount.doOnTextChanged { text, _, _, _ ->
                if (text.toString().isEmpty()) {
                    sbSavingsPercent.setProgress(0.toFloat())
                } else if (text.toString().isNotEmpty() && text.toString().toInt() > MONEY_AMOUNT) {
                    Toast.makeText(
                        context,
                        "Вы не можете сберечь больше $MONEY_AMOUNT суммы",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val percent =
                        (text.toString().toInt().toFloat() * 100F) / MONEY_AMOUNT.toFloat()
                    sbSavingsPercent.setProgress(percent)
                }
            }

            sbUnexpectedPercent.onSeekChangeListener = object : OnSeekChangeListener {
                override fun onSeeking(p0: SeekParams?) {
                    if (p0 != null) {
                        val amount = (p0.progressFloat * MONEY_AMOUNT) / 100
                        if (amount == 0F) {
                            etUnexpectedAmount.setText("")
                        } else {
                            etUnexpectedAmount.setText(amount.toInt().toString())
                        }
                        etUnexpectedAmount.setSelection(etUnexpectedAmount.length())
                    }
                }

                override fun onStartTrackingTouch(p0: IndicatorSeekBar?) {

                }

                override fun onStopTrackingTouch(p0: IndicatorSeekBar?) {

                }

            }

            etUnexpectedAmount.doOnTextChanged { text, _, _, _ ->
                if (text.toString().isEmpty()) {
                    sbUnexpectedPercent.setProgress(0.toFloat())
                } else if (text.toString().isNotEmpty() && text.toString().toInt() > MONEY_AMOUNT) {
                    Toast.makeText(
                        context,
                        "Вы не можете сберечь больше $MONEY_AMOUNT суммы",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val percent =
                        (text.toString().toInt().toFloat() * 100F) / MONEY_AMOUNT.toFloat()
                    sbUnexpectedPercent.setProgress(percent)
                }
            }
        }
    }

    private fun initViews() {
        binding.run {
            firstStepDistribution.apply {
                tvStepTitle.text = getString(R.string.start_progress_first_step)
                tvStepNumber.text = "1"
            }
            secondStepDistribution.apply {
                tvStepTitle.text = getString(R.string.start_progress_second_step)
                tvStepNumber.text = "2"
            }
            thirdStepDistribution.apply {
                tvStepTitle.text = getString(R.string.start_progress_third_step)
                tvStepNumber.text = "3"
            }
            sbSavingsPercent.setIndicatorTextFormat("\${PROGRESS} %")
            sbUnexpectedPercent.setIndicatorTextFormat("\${PROGRESS} %")
            val adapter = ExpensesAdapter()
            adapter.submitList(expensesList)
            val totalSum = expensesList.sumBy { it.totalSum ?: 0 }
            tvTotal.text = getString(R.string.total_price, totalSum.toString())
            rvServices.adapter = adapter
            //todo disable "Next" button when logic is ready
//            btnNext.disable()
            stepOnCompleted()
            stepOnFocus()
        }
    }

    private fun openServices() {
        val bundle = Bundle()
        bundle.putSerializable(SCREEN, Screen.SERVICES)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

    private fun stepOnCompleted() {
        binding.firstStepDistribution.ivStepIcon.setStepDone()
        binding.firstStepDistribution.tvStepNumber.hide()
    }

    private fun stepOnFocus() {
        binding.secondStepDistribution.ivStepIcon.setStepActive()
        binding.secondStepDistribution.tvStepNumber.setStepActive()
        binding.secondStepDistribution.tvStepTitle.setStepActive()
    }

}