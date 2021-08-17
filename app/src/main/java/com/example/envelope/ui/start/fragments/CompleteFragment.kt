package com.example.envelope.ui.start.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.envelope.R
import com.example.envelope.databinding.FragmentCompleteBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.totalSum

class CompleteFragment :
    BindingFragment<FragmentCompleteBinding>(FragmentCompleteBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            tvTotalAmount.text = String.format(
                getString(R.string.finish_set_up_caution),
                totalSum.toString()
            )

            includeNav.apply {
                btnBack.setOnClickListener {
                    activity?.onBackPressed()
                }
                btnNext.setOnClickListener {
                    //todo implement
                    Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show()
                }
                btnReturn.setOnClickListener {
                    (activity as StartActivity).restart()
                }
            }
        }
    }

}