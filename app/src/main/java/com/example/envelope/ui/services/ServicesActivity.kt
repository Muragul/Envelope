package com.example.envelope.ui.services

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import com.example.envelope.databinding.ActivityServicesBinding
import com.example.envelope.databinding.AddExtraServiceDialogBinding
import com.example.envelope.utils.binding.BindingActivity
import com.example.envelope.utils.servicesList

class ServicesActivity :
    BindingActivity<ActivityServicesBinding>(ActivityServicesBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            val adapter = ServicesAdapter()
            val servicesList = servicesList
            adapter.submitList(servicesList)
            rvServices.adapter = adapter

            ivAdd.setOnClickListener {
                showAddServiceDialog()
            }
        }
    }

    private fun showAddServiceDialog() {
        val dialog = Dialog(binding.rvServices.context)
        val dialogBinding = AddExtraServiceDialogBinding.inflate(layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.btnAdd.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}