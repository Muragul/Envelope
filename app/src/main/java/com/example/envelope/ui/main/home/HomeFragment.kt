package com.example.envelope.ui.main.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.envelope.R
import com.example.envelope.databinding.FragmentHomeBinding
import com.example.envelope.ui.ContainerActivity
import com.example.envelope.ui.main.home.hints.BalloonFactory
import com.example.envelope.utils.*
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.show
import com.example.envelope.utils.navigation.Screen
import com.skydoves.balloon.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var prefs: SharedPreferences
    private lateinit var budgetHint: Balloon
    private lateinit var unexpectedHint: Balloon
    private lateinit var savingsHint: Balloon

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = context?.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)!!
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
            ltEnvelopeFirstWeek.root.setOnClickListener {
                openBudget()
            }
            ivBudgetHint.setOnClickListener {
                val locationArray = IntArray(2)
                ivBudgetHint.getLocationOnScreen(locationArray)
                val balloonBudgetHint =
                    BalloonFactory(
                        ivBudgetHint,
                        getString(R.string.budget_hint),
                        locationArray[0]
                    ).create(
                        ivBudgetHint.context,
                        this@HomeFragment
                    )
                balloonBudgetHint.showAlignBottom(
                    ivBudgetHint
                )
            }
            ivUnexpectedHint.setOnClickListener {
                val locationArray = IntArray(2)
                ivUnexpectedHint.getLocationOnScreen(locationArray)
                val balloonUnexpectedHint =
                    BalloonFactory(
                        ivUnexpectedHint,
                        getString(R.string.unexpected_hint),
                        locationArray[0]
                    ).create(
                        ivUnexpectedHint.context,
                        this@HomeFragment
                    )
                balloonUnexpectedHint.showAlignBottom(
                    ivUnexpectedHint
                )
            }
            ivSavingsHint.setOnClickListener {
                val locationArray = IntArray(2)
                ivSavingsHint.getLocationOnScreen(locationArray)
                val balloonSavingsHint =
                    BalloonFactory(
                        ivSavingsHint,
                        getString(R.string.savings_hint),
                        locationArray[0]
                    ).create(
                        ivSavingsHint.context,
                        this@HomeFragment
                    )
                balloonSavingsHint.showAlignBottom(
                    ivSavingsHint
                )
            }
        }
    }

    private fun initViews() {
        binding.run {
            setupBudgetData()
            setupHints()
            setupHomeExpensesAdapter()
            tvBudgetAmount.text = String.format(
                getString(R.string.sum_with_t),
                MONEY_AMOUNT.toString()
            )
        }
    }

    private fun setupHomeExpensesAdapter() {
        binding.run {
            val adapter = HomeExpensesAdapter(
                itemClick = { openPayment() }
            )
            adapter.submitList(expensesList)
            rvExpenses.adapter = adapter
        }
    }

    private fun setupHints() {
        binding.run {
            ivUnexpectedHint.post {
                val locationArray = IntArray(2)
                ivUnexpectedHint.getLocationOnScreen(locationArray)
                unexpectedHint =
                    BalloonFactory(
                        ivUnexpectedHint,
                        getString(R.string.unexpected_hint),
                        locationArray[0]
                    ).create(
                        ivUnexpectedHint.context,
                        this@HomeFragment
                    )
            }
            ivSavingsHint.post {
                val locationArray = IntArray(2)
                ivSavingsHint.getLocationOnScreen(locationArray)
                savingsHint =
                    BalloonFactory(
                        ivSavingsHint,
                        getString(R.string.savings_hint),
                        locationArray[0]
                    ).create(
                        ivSavingsHint.context,
                        this@HomeFragment
                    )
            }
            ivBudgetHint.post {
                val locationArray = IntArray(2)
                ivBudgetHint.getLocationOnScreen(locationArray)
                budgetHint =
                    BalloonFactory(
                        ivBudgetHint,
                        getString(R.string.budget_hint),
                        locationArray[0]
                    ).create(
                        ivBudgetHint.context,
                        this@HomeFragment
                    )
                Handler(Looper.getMainLooper()).postDelayed({
                    if (!prefs.contains("hintShowed")) {
                        budgetHint.relayShowAlignBottom(unexpectedHint, ivUnexpectedHint)
                            .relayShowAlignBottom(savingsHint, ivSavingsHint)
                        budgetHint.showAlignBottom(ivBudgetHint)
                        prefs.edit().putBoolean("hintShowed", true).apply()
                    }
                }, 100)
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

    private fun setupBudgetData() {
        val calendar = Calendar.getInstance()
        val today = Calendar.getInstance().time
        calendar.time = today
        calendar.add(Calendar.DATE, 30)

        val calendar1 = Calendar.getInstance()
        calendar1.time = today
        calendar1.add(Calendar.DATE, 5)

        val calendar2 = Calendar.getInstance()
        calendar1.add(Calendar.DATE, 1)
        calendar2.time = calendar1.time

        val calendar3 = Calendar.getInstance()
        calendar2.add(Calendar.DATE, 1)
        calendar3.time = calendar2.time
        calendar3.add(Calendar.DATE, 6)

        val calendar4 = Calendar.getInstance()
        calendar3.add(Calendar.DATE, 1)
        calendar4.time = calendar3.time

        val calendar5 = Calendar.getInstance()
        calendar4.add(Calendar.DATE, 1)
        calendar5.time = calendar4.time
        calendar5.add(Calendar.DATE, 6)

        val calendar6 = Calendar.getInstance()
        calendar5.add(Calendar.DATE, 1)
        calendar6.time = calendar5.time

        val calendar7 = Calendar.getInstance()
        calendar6.add(Calendar.DATE, 1)
        calendar7.time = calendar6.time
        calendar7.add(Calendar.DATE, 6)

        binding.run {
            ltEnvelopeFirstWeek.tvEnvelopeDateRange.text =
                getString(
                    R.string.dateRange,
                    SimpleDateFormat("d.MM", Locale("ru")).format(today),
                    SimpleDateFormat("d.MM", Locale("ru")).format(calendar1.time)
                )

            ltEnvelopeSecondWeek.tvEnvelopeDateRange.text =
                getString(
                    R.string.dateRange,
                    SimpleDateFormat("d.MM", Locale("ru")).format(calendar2.time),
                    SimpleDateFormat("d.MM", Locale("ru")).format(calendar3.time)
                )

            ltEnvelopeThirdWeek.tvEnvelopeDateRange.text =
                getString(
                    R.string.dateRange,
                    SimpleDateFormat("d.MM", Locale("ru")).format(calendar4.time),
                    SimpleDateFormat("d.MM", Locale("ru")).format(calendar5.time)
                )

            ltEnvelopeFourthWeek.tvEnvelopeDateRange.text =
                getString(
                    R.string.dateRange,
                    SimpleDateFormat("d.MM", Locale("ru")).format(calendar6.time),
                    SimpleDateFormat("d.MM", Locale("ru")).format(calendar7.time)
                )

            tvDateRange.text = getString(
                R.string.dateRange,
                SimpleDateFormat("d MMMM", Locale("ru")).format(today),
                SimpleDateFormat("d MMMM", Locale("ru")).format(calendar.time)
            )
            ltEnvelopeFirstWeek.ltCurrentWeekIndicator.show()
            ltEnvelopeFirstWeek.tvWeekNumberTitle.text = getString(R.string.week_number, "1")
            ltEnvelopeSecondWeek.tvWeekNumberTitle.text = getString(R.string.week_number, "2")
            ltEnvelopeThirdWeek.tvWeekNumberTitle.text = getString(R.string.week_number, "3")
            ltEnvelopeFourthWeek.tvWeekNumberTitle.text = getString(R.string.week_number, "4")

            ltEnvelopeFirstWeek.tvWeekAmount.text = getString(R.string.total_price, "23 650")
            ltEnvelopeSecondWeek.tvWeekAmount.text = getString(R.string.total_price, "23 650")
            ltEnvelopeThirdWeek.tvWeekAmount.text = getString(R.string.total_price, "23 650")
            ltEnvelopeFourthWeek.tvWeekAmount.text = getString(R.string.total_price, "23 650")
        }
    }
}