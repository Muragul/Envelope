package com.example.envelope.ui.budget

import com.example.envelope.R
import com.example.envelope.data.Budget
import com.example.envelope.databinding.ItemBudgetBinding
import com.example.envelope.utils.binding.BindingAdapter
import com.example.envelope.utils.extensions.hide

class BudgetAdapter : BindingAdapter<Budget, ItemBudgetBinding>(ItemBudgetBinding::inflate) {
    override fun bind(item: Budget, binding: ItemBudgetBinding) {
        super.bind(item, binding)
        //todo это вся логика чисто для показа юай потом обязательно уберу)))
        if (item.weekSentDate != 0) {
            binding.tvSentDate.text = String.format(
                binding.tvSentDate.context.getString(R.string.sent_date),
                item.weekSentDate.toString(),
                getDayAddition(item.weekSentDate)
            )
        } else {
            binding.tvSentDate.text = "Отправлено на карту"
        }
        if (item.weekNumber != 1) {
            binding.ltWeekIndicator.hide()
        }
        if (item.weekNumber == 1) {
            binding.btnWithdrawEarly.hide()
        }
        binding.tvWeekNumber.text = String.format(
            binding.tvWeekNumber.context.getString(R.string.week_number),
            item.weekNumber.toString()
        )
        binding.tvWeekRange.text = item.weekRange
        binding.tvWeeklyCashAmount.text = item.weekBudget
    }

    private fun getDayAddition(num: Int): String {
        val preLastDigit = num % 100 / 10
        return if (preLastDigit == 1) {
            "дней"
        } else when (num % 10) {
            1 -> "день"
            2, 3, 4 -> "дня"
            else -> "дней"
        }
    }
}