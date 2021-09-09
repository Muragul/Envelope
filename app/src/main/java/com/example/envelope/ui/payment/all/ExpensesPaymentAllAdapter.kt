package com.example.envelope.ui.payment.all

import com.example.envelope.R
import com.example.envelope.data.Expense
import com.example.envelope.databinding.ItemExpensesListPaymentAllBinding
import com.example.envelope.utils.binding.BindingAdapter
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.loadUrl
import com.example.envelope.utils.extensions.show

class ExpensesPaymentAllAdapter :
    BindingAdapter<Expense, ItemExpensesListPaymentAllBinding>(ItemExpensesListPaymentAllBinding::inflate) {
    override fun bind(item: Expense, binding: ItemExpensesListPaymentAllBinding) {
        binding.run {
            if (item.imageUrl.isNullOrEmpty())
                cvServiceView.hide()
            else {
                cvServiceView.show()
                ivServiceIcon.loadUrl(item.imageUrl)
            }
            tvServiceTitle.text = item.title
            tvServicePrice.text = String.format(
                tvServicePrice.context.getString(R.string.total_price),
                item.totalSum.toString()
            )
        }
    }
}