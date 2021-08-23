package com.example.envelope.ui.main.profile

import android.os.Bundle
import android.view.View
import com.example.envelope.databinding.FragmentProfileBinding
import com.example.envelope.utils.binding.BindingFragment

class ProfileFragment : BindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {}
    }
}