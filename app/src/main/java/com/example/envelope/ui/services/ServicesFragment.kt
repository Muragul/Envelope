package com.example.envelope.ui.services

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.envelope.data.Service
import com.example.envelope.databinding.AddExtraServiceDialogBinding
import com.example.envelope.databinding.ChooseServiceDialogBinding
import com.example.envelope.databinding.FragmentServicesBinding
import com.example.envelope.utils.binding.BindingFragment
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.loadUrl
import com.example.envelope.utils.servicesList

class ServicesFragment :
    BindingFragment<FragmentServicesBinding>(FragmentServicesBinding::inflate) {

    companion object {
        fun newInstance(bundle: Bundle?): ServicesFragment {
            val fragment = ServicesFragment()
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
            ivAdd.setOnClickListener {
                showAddServiceDialog()
            }

            ivBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun initViews() {
        binding.run {
            val adapter = ServicesAdapter(serviceItemClick = {
                openDialog(it)
            })
            val servicesList = servicesList
            adapter.submitList(servicesList)
            rvServices.adapter = adapter
        }
    }

    private fun openDialog(item: Service) {
        val dialog = Dialog(binding.rvServices.context)
        val dialogBinding = ChooseServiceDialogBinding.inflate(layoutInflater)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.item.ivServiceIcon.loadUrl(item.imageUrl)
        dialogBinding.item.divider.hide()
        dialogBinding.item.tvServiceTitle.text = item.title
        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.btnAdd.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
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
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}