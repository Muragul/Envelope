package com.example.envelope.ui.main.history

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentHistoryBinding
import com.example.envelope.utils.binding.BindingFragment

class HistoryFragment : BindingFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {}
    }
}