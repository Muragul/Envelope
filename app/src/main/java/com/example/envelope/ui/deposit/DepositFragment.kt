package com.example.envelope.ui.deposit

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentDepositBinding
import com.example.envelope.utils.SCREEN_TITLE
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.depositList

class DepositFragment : BindingFragment<FragmentDepositBinding>(FragmentDepositBinding::inflate) {

    companion object {
        fun newInstance(bundle: Bundle?): DepositFragment {
            val fragment = DepositFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        binding.run {
            toolbar.ivBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun initViews() {
        binding.run {
            val title = arguments?.getString(SCREEN_TITLE)
            val adapter = DepositAdapter()
            adapter.submitList(depositList)
            rvDeposit.adapter = adapter
            toolbar.tvToolbarTitle.text = title.toString()
        }
    }
}