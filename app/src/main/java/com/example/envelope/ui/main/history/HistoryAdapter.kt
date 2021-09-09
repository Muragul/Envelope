package com.example.envelope.ui.main.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.envelope.R
import com.example.envelope.data.Transaction
import com.example.envelope.data.TransactionHeader
import com.example.envelope.data.TransactionMarker
import com.example.envelope.databinding.ItemExpensesListBinding
import com.example.envelope.databinding.ItemHistoryHeaderBinding
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.loadUrl
import com.example.envelope.utils.extensions.show

class HistoryAdapter(
    private val transactionHistory: List<TransactionMarker>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val HEADER = 0
        private const val ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                val binding = ItemHistoryHeaderBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            ITEM -> {
                val binding = ItemExpensesListBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                TransactionViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = transactionHistory[position]) {
            is TransactionHeader -> (holder as HeaderViewHolder).bind(item)
            is Transaction -> (holder as TransactionViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int = transactionHistory.size

    override fun getItemViewType(position: Int): Int {
        return when (transactionHistory[position]) {
            is TransactionHeader -> HEADER
            is Transaction -> ITEM
            else -> throw IllegalArgumentException("Unknown type")
        }
    }

    inner class HeaderViewHolder(val binding: ItemHistoryHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TransactionHeader) {
            binding.tvDate.text = item.date
        }
    }

    inner class TransactionViewHolder(val binding: ItemExpensesListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction) {
            binding.run {
                if (item.icon.isNullOrEmpty()) {
                    cvServiceIcon.hide()
                } else {
                    cvServiceIcon.show()
                }
                ivServiceIcon.loadUrl(item.icon)
                tvServiceTitle.text = item.title
                if (item.amount < 0) {
                    tvServicePrice.setTextColor(
                        ContextCompat.getColor(tvServicePrice.context, R.color.red)
                    )
                } else {
                    tvServicePrice.setTextColor(
                        ContextCompat.getColor(tvServicePrice.context, R.color.green)
                    )
                }
                tvServicePrice.text = String.format(
                    ivServiceIcon.context.getString(R.string.total_price),
                    item.amount.toString()
                )
            }
        }
    }

}