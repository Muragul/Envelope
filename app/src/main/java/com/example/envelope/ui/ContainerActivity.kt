package com.example.envelope.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.envelope.databinding.ActivityContainerBinding
import com.example.envelope.utils.SCREEN
import com.example.envelope.utils.binding.BindingActivity
import com.example.envelope.utils.navigation.FragmentNavigator
import com.example.envelope.utils.navigation.Screen

class ContainerActivity :
    BindingActivity<ActivityContainerBinding>(ActivityContainerBinding::inflate) {

    companion object {

        fun start(
            context: Context?,
            bundle: Bundle? = null,
            requestCode: Int? = null,
            flag: Int? = null
        ) {
            val intent = Intent(context, ContainerActivity::class.java)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            if (flag != null) {
                intent.flags = flag
            }
            if (context is Activity) {
                if (requestCode == null) {
                    context.startActivity(intent)
                } else {
                    context.startActivityForResult(intent, requestCode)
                }
            }
        }

        fun start(
            fragment: Fragment?,
            bundle: Bundle? = null,
            requestCode: Int? = null,
            flag: Int? = null
        ) {
            val intent = Intent(fragment?.activity, ContainerActivity::class.java)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            if (flag != null) {
                intent.flags = flag
            }
            if (requestCode == null) {
                fragment?.startActivity(intent)
            } else {
                fragment?.startActivityForResult(intent, requestCode)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screen = intent.getSerializableExtra(SCREEN) as Screen
        FragmentNavigator.openFragment(
            activity = this,
            screen = screen,
            data = intent.extras
        )
        binding.run { }
    }

    fun showFragment(fragment: Fragment, tag: String? = null) {
        changeFragment(fragment = fragment, tag = tag, container = binding.baseContainer.id)
    }
}