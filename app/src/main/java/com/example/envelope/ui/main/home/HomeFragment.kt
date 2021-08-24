package com.example.envelope.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.envelope.databinding.FragmentHomeBinding
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.expensesList

class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            val adapter = HomeExpensesAdapter()
            adapter.submitList(expensesList)
            rvExpenses.adapter = adapter
            rvExpenses.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}