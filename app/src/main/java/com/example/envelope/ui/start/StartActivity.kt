package com.example.envelope.ui.start

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.envelope.databinding.ActivityStartBinding
import com.example.envelope.ui.main.MainActivity
import com.example.envelope.ui.start.amount.AmountFragment
import com.example.envelope.utils.binding.BindingActivity

class StartActivity : BindingActivity<ActivityStartBinding>(ActivityStartBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        showFragment(fragment = AmountFragment())
    }

    fun showFragment(fragment: Fragment, tag: String? = null) {
        changeFragment(fragment = fragment, tag = tag, container = binding.container.id)
    }

    fun restart() {
        startActivity(Intent(this, StartActivity::class.java))
        finish()
    }

    fun completeSetup() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}