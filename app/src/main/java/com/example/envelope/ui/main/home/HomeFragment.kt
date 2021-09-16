package com.example.envelope.ui.main.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.example.envelope.R
import com.example.envelope.databinding.FragmentHomeBinding
import com.example.envelope.ui.ContainerActivity
import com.example.envelope.ui.main.home.hints.BalloonFactory
import com.example.envelope.utils.*
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.show
import com.example.envelope.utils.navigation.Screen
import com.skydoves.balloon.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var prefs: SharedPreferences
    private lateinit var budgetHint: Balloon
    private lateinit var unexpectedHint: Balloon
    private lateinit var savingsHint: Balloon

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = context?.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)!!
        prefs.edit().clear().apply()
        initViews()
        binding.run {
            val locationArrayBudget = IntArray(2)
            ivBudgetHint.getLocationOnScreen(locationArrayBudget)
            budgetHint = BalloonFactory(
                ivUnexpectedHint,
                getString(R.string.unexpected_hint),
                ivBudgetHint.x.toInt()
            )
                .create(ivUnexpectedHint.context, this@HomeFragment)

            val locationArrayExpenses = IntArray(2)
            ivUnexpectedHint.getLocationOnScreen(locationArrayExpenses)
            unexpectedHint = BalloonFactory(
                ivUnexpectedHint,
                getString(R.string.unexpected_hint),
                locationArrayExpenses[0]
            )
                .create(ivUnexpectedHint.context, this@HomeFragment)

            val locationArraySavings = IntArray(2)
            ivSavingsHint.getLocationOnScreen(locationArraySavings)
            savingsHint = BalloonFactory(
                ivSavingsHint,
                getString(R.string.savings_hint),
                locationArraySavings[0]
            )
                .create(ivSavingsHint.context, this@HomeFragment)

            if (!prefs.contains("hintShowed")) {
                Log.d("asdasd", "asdadasd")
                ivBudgetHint.performClick()
                budgetHint.relayShowAlignBottom(unexpectedHint, ivUnexpectedHint)
                    .relayShowAlignBottom(savingsHint, ivSavingsHint)
                ivBudgetHint.performClick()
                ivBudgetHint.setOnClickListener {
                    budgetHint.showAlignBottom(ivBudgetHint)
                }
                prefs.edit().putBoolean("hintShowed", true).apply()
            }
            setupListeners()
        }
    }

    private fun setupListeners() {
        binding.run {
            tvAllExpenses.setOnClickListener {
                openServices()
            }
            btnCreateDeposit.setOnClickListener {
                openDeposit()
            }
            ltEnvelopeFirstWeek.root.setOnClickListener {
                openBudget()
            }
            ivBudgetHint.setOnClickListener {
                val locationArray = IntArray(2)
                ivBudgetHint.getLocationOnScreen(locationArray)
                budgetHint
                    .showAlignBottom(
                        ivBudgetHint
                    )
            }
            ivUnexpectedHint.setOnClickListener {
                val locationArray = IntArray(2)
                ivUnexpectedHint.getLocationOnScreen(locationArray)
                unexpectedHint
                    .showAlignBottom(
                        ivUnexpectedHint
                    )
            }
            ivSavingsHint.setOnClickListener {
                val locationArray = IntArray(2)
                ivSavingsHint.getLocationOnScreen(locationArray)
                savingsHint
                    .showAlignBottom(
                        ivSavingsHint
                    )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViews() {
        val dtf = DateTimeFormatter.ofPattern("d MMMM")
        val dtfDM = DateTimeFormatter.ofPattern("d.MM")
        val now = LocalDateTime.now()
        val end = now.plusDays(30)
        binding.run {
            val adapter = HomeExpensesAdapter(
                itemClick = { id: Int ->
                    openPayment(id)
                }
            )
            adapter.submitList(expensesList)
            rvExpenses.adapter = adapter
            tvDateRange.text = getString(R.string.dateRange, dtf.format(now), dtf.format(end))
            tvBudgetAmount.text = MONEY_AMOUNT.toString()
            ltEnvelopeFirstWeek.ltCurrentWeekIndicator.show()
            ltEnvelopeFirstWeek.tvWeekNumberTitle.text = getString(R.string.week_number, "1")
            ltEnvelopeSecondWeek.tvWeekNumberTitle.text = getString(R.string.week_number, "2")
            ltEnvelopeThirdWeek.tvWeekNumberTitle.text = getString(R.string.week_number, "3")
            ltEnvelopeFourthWeek.tvWeekNumberTitle.text = getString(R.string.week_number, "4")

            ltEnvelopeFirstWeek.tvWeekAmount.text = getString(R.string.total_price, "23 650")
            ltEnvelopeSecondWeek.tvWeekAmount.text = getString(R.string.total_price, "23 650")
            ltEnvelopeThirdWeek.tvWeekAmount.text = getString(R.string.total_price, "23 650")
            ltEnvelopeFourthWeek.tvWeekAmount.text = getString(R.string.total_price, "23 650")

            ltEnvelopeFirstWeek.tvEnvelopeDateRange.text =
                getString(R.string.dateRange, dtfDM.format(now), dtfDM.format(now.plusDays(7)))

            ltEnvelopeSecondWeek.tvEnvelopeDateRange.text =
                getString(
                    R.string.dateRange,
                    dtfDM.format(now.plusDays(8)),
                    dtfDM.format(now.plusDays(14))
                )

            ltEnvelopeThirdWeek.tvEnvelopeDateRange.text =
                getString(
                    R.string.dateRange,
                    dtfDM.format(now.plusDays(15)),
                    dtfDM.format(now.plusDays(22))
                )

            ltEnvelopeFourthWeek.tvEnvelopeDateRange.text =
                getString(
                    R.string.dateRange,
                    dtfDM.format(now.plusDays(23)),
                    dtfDM.format(now.plusDays(30))
                )
        }
    }


//    private fun createToolTip(view: View, hintText: String, x: Int): Balloon {
//        val vm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val metrics = DisplayMetrics()
//        vm.defaultDisplay.getMetrics(metrics)
//        val tooltipWidth = metrics.widthPixels
//        val arrowLocation: Double = x.toDouble() / (tooltipWidth.toDouble() - 60.toDouble())
//        return createBalloon(view.context) {
//            setArrowSize(10)
//            setWidth(tooltipWidth)
//            setHeight(BalloonSizeSpec.WRAP)
//            setArrowPosition(arrowLocation.toFloat())
//            setArrowSize(20)
//            setArrowDrawable(ContextCompat.getDrawable(view.context, R.drawable.tool_tip_arrow))
//            setCornerRadius(4f)
//            setText(hintText)
//            setTextSize(14f)
//            setTextGravity(Gravity.START)
//            setTextColorResource(R.color.black)
//            setPadding(12)
//            setMarginTop(0)
//            setMarginHorizontal(15)
//            setBackgroundColorResource(R.color.light_blue)
//            setBalloonAnimation(BalloonAnimation.FADE)
//            setLifecycleOwner(lifecycleOwner)
//            setIsVisibleOverlay(true) // sets the visibility of the overlay for highlighting an anchor.
//            setOverlayColorResource(R.color.overlay) // background color of the overlay using a color resource.
//            setOverlayPadding(6f) // sets a padding value of the overlay shape internally.
//            setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE) // default is fade.
//            setDismissWhenOverlayClicked(false) // disable dismissing the balloon when the overlay is clicked.
//        }
//    }

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