package com.example.envelope.ui.budget

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentBudgetBinding
import com.example.envelope.utils.SCREEN_TITLE
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.budgetList

class BudgetFragment : BindingFragment<FragmentBudgetBinding>(FragmentBudgetBinding::inflate) {
    companion object {
        fun newInstance(bundle: Bundle?): BudgetFragment {
            val fragment = BudgetFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.run {
            toolbar.ivBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun initViews() {
        val title = arguments?.getString(SCREEN_TITLE)
        binding.run {
            val adapter = BudgetAdapter()
            adapter.submitList(budgetList)
            rvBudget.adapter = adapter
            toolbar.tvToolbarTitle.text = title.toString()
        }
    }
}