package com.example.envelope.ui.start.distribution

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.example.envelope.R
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
                if (ltExpensesContent.visibility == View.GONE) {
                    ltExpenses.setBackgroundColor(
                        ContextCompat.getColor(
                            ltExpenses.context,
                            R.color.white_grey
                        )
                    )
                    tvTitleExpenses.setTextColor(
                        ContextCompat.getColor(
                            tvTitleExpenses.context,
                            R.color.black
                        )
                    )
                    vDividerExpenses.hide()
                    //todo animation
                    ltExpensesContent.show()
                    ltExpensesContent.startAnimation(
                        AnimationUtils.loadAnimation(
                            context,
                            R.anim.slide_up
                        )
                    )
                } else {
                    ltExpenses.setBackgroundColor(
                        ContextCompat.getColor(
                            ltExpenses.context,
                            R.color.main_page_background_dark
                        )
                    )
                    tvTitleExpenses.setTextColor(
                        ContextCompat.getColor(
                            tvTitleExpenses.context,
                            R.color.white_grey
                        )
                    )
                    ltExpensesContent.hide()
                    vDividerExpenses.show()
                }
            }

            ltSavings.setOnClickListener {
                //todo refactor: add toggle extension
                if (ltSavingsContent.visibility == View.GONE) {
                    ltSavings.setBackgroundColor(
                        ContextCompat.getColor(
                            ltSavings.context,
                            R.color.white_grey
                        )
                    )
                    tvSavings.setTextColor(
                        ContextCompat.getColor(
                            ltSavings.context,
                            R.color.black
                        )
                    )
                    ltSavingsContent.show()
                    vDividerSavings.hide()
                    ltSavingsContent.startAnimation(
                        AnimationUtils.loadAnimation(
                            context,
                            R.anim.slide_up
                        )
                    )
                } else {
                    ltSavings.setBackgroundColor(
                        ContextCompat.getColor(
                            ltSavings.context,
                            R.color.main_page_background_dark
                        )
                    )
                    tvSavings.setTextColor(
                        ContextCompat.getColor(
                            tvSavings.context,
                            R.color.white_grey
                        )
                    )
                    ltSavingsContent.hide()
                    vDividerSavings.show()
                }
            }

            ltUnexpected.setOnClickListener {
                //todo refactor: add toggle extension
                if (ltUnexpectedContent.visibility == View.GONE) {
                    ltUnexpected.setBackgroundColor(
                        ContextCompat.getColor(
                            ltUnexpected.context,
                            R.color.white_grey
                        )
                    )
                    tvTitleUnexpected.setTextColor(
                        ContextCompat.getColor(
                            tvTitleUnexpected.context,
                            R.color.black
                        )
                    )
                    ltUnexpectedContent.show()
                    ltUnexpectedContent.startAnimation(
                        AnimationUtils.loadAnimation(
                            context,
                            R.anim.slide_up
                        )
                    )
                } else {
                    ltUnexpected.setBackgroundColor(
                        ContextCompat.getColor(
                            ltUnexpected.context,
                            R.color.main_page_background_dark
                        )
                    )
                    tvTitleUnexpected.setTextColor(
                        ContextCompat.getColor(
                            tvTitleUnexpected.context,
                            R.color.white_grey
                        )
                    )
                    ltUnexpectedContent.hide()
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
            val adapter = ExpensesAdapter()
            adapter.submitList(expensesList)
            rvServices.adapter = adapter
            //todo disable "Next" button when logic is ready
//            btnNext.disable()
            stepOnCompleted(1)
            stepOnFocus(2)
        }
    }

    private fun openServices() {
        val bundle = Bundle()
        bundle.putSerializable(SCREEN, Screen.SERVICES)
        ContainerActivity.start(fragment = this, bundle = bundle, requestCode = REQUEST_CODE)
    }

    fun stepOnCompleted(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_done)
                binding.firstStepDistribution.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.firstStepDistribution.tvStepNumber.hide()
            }
            2 -> {
                binding.secondStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_done)
                binding.secondStepDistribution.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.secondStepDistribution.tvStepNumber.hide()
            }
            3 -> {
                binding.thirdStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_done)
                binding.thirdStepDistribution.ivStepIcon.setImageResource(R.drawable.ic_baseline_check_24)
                binding.thirdStepDistribution.tvStepNumber.hide()
            }
        }
    }

    fun stepOnFocus(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.progress_bg
                    )
                binding.firstStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
                binding.firstStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
            }
            2 -> {

                binding.secondStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg)
                binding.secondStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
                binding.secondStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )

            }
            3 -> {

                binding.thirdStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg)
                binding.thirdStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
                binding.thirdStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(binding.root.context, R.color.button_light_blue)
                )
            }
        }
    }

    fun makeStepDefault(step: Int) {
        when (step) {
            1 -> {
                binding.firstStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_default)
                binding.firstStepDistribution.ivStepIcon.setImageResource(0)
                binding.firstStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
                binding.firstStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
            }
            2 -> {
                binding.secondStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_default)
                binding.secondStepDistribution.ivStepIcon.setImageResource(0)
                binding.secondStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
                binding.secondStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
            }
            3 -> {
                binding.thirdStepDistribution.ivStepIcon.background =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.progress_bg_default)
                binding.thirdStepDistribution.ivStepIcon.setImageResource(0)
                binding.thirdStepDistribution.tvStepNumber.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
                binding.thirdStepDistribution.tvStepTitle.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.text_light_purple
                    )
                )
            }
        }
    }
}