package com.example.envelope.ui.start.distribution

import com.example.envelope.R
import com.example.envelope.data.Expense
import com.example.envelope.databinding.ItemExpensesListDistributionBinding
import com.example.envelope.utils.binding.BindingAdapter
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.loadUrl
import com.example.envelope.utils.extensions.show

class ExpensesAdapter :
    BindingAdapter<Expense, ItemExpensesListDistributionBinding>(ItemExpensesListDistributionBinding::inflate) {
    override fun bind(item: Expense, binding: ItemExpensesListDistributionBinding) {
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