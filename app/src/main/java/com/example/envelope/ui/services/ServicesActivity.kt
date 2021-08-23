package com.example.envelope.ui.services

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import com.example.envelope.R
import com.example.envelope.databinding.ActivityServicesBinding
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
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_extra_service_dialog)
        dialog.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<Button>(R.id.btn_add).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}