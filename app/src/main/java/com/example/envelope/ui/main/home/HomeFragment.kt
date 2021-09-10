package com.example.envelope.ui.main.home

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.envelope.R
import com.example.envelope.databinding.FragmentHomeBinding
import com.example.envelope.ui.ContainerActivity
import com.example.envelope.utils.*
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.navigation.Screen
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.createBalloon

class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        binding.run {
            tvAllExpenses.setOnClickListener {
                openServices()
            }
            btnCreateDeposit.setOnClickListener {
                openDeposit()
            }

            btnBudget.ltRoot.setOnClickListener {
                openBudget()
            }
            ivBudgetHint.setOnClickListener {
                val locationArray = IntArray(2)
                ivBudgetHint.getLocationOnScreen(locationArray)
                createToolTip(
                    binding.ivBudgetHint,
                    getString(R.string.budget_hint),
                    locationArray[0]
                ).showAlignBottom(
                    ivBudgetHint
                )
            }
            ivUnexpectedHint.setOnClickListener {
                val locationArray = IntArray(2)
                ivUnexpectedHint.getLocationOnScreen(locationArray)
                createToolTip(
                    binding.ivUnexpectedHint,
                    getString(R.string.unexpected_hint),
                    locationArray[0]
                ).showAlignBottom(ivUnexpectedHint)
            }
            ivSavingsHint.setOnClickListener {
                val locationArray = IntArray(2)
                ivSavingsHint.getLocationOnScreen(locationArray)
                createToolTip(
                    binding.ivSavingsHint,
                    getString(R.string.savings_hint),
                    locationArray[0]
                ).showAlignBottom(
                    ivSavingsHint
                )
            }
        }
    }

    private fun initViews() {
        binding.run {
            val adapter = HomeExpensesAdapter(
                itemClick = { id: Int ->
                    openPayment(id)
                }
            )
            adapter.submitList(expensesList)
            rvExpenses.adapter = adapter
        }
    }

    private fun createToolTip(view: View, hintText: String, x: Int): Balloon {
        val vm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        vm.defaultDisplay.getMetrics(metrics)
        val tooltipWidth = metrics.widthPixels
        val arrowLocation: Double = x.toDouble() / (tooltipWidth.toDouble() - 60.toDouble())
        return createBalloon(view.context) {
            setArrowSize(10)
            setWidth(tooltipWidth)
            setHeight(BalloonSizeSpec.WRAP)
            setArrowPosition(arrowLocation.toFloat())
            setArrowSize(20)
            setArrowDrawable(ContextCompat.getDrawable(view.context, R.drawable.tool_tip_arrow))
            setCornerRadius(4f)
            setText(hintText)
            setTextSize(14f)
            setTextGravity(Gravity.START)
            setTextColorResource(R.color.black)
            setPadding(12)
            setMarginTop(0)
            setMarginHorizontal(15)
            setBackgroundColorResource(R.color.light_blue)
            setBalloonAnimation(BalloonAnimation.FADE)
            setLifecycleOwner(lifecycleOwner)
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