package com.example.envelope.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.envelope.R
import com.example.envelope.databinding.ActivityMainBinding
import com.example.envelope.ui.main.history.HistoryFragment
import com.example.envelope.ui.main.home_compose.HomeComposeFragment
import com.example.envelope.ui.main.profile.ProfileFragment
import com.example.envelope.utils.binding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    companion object {
        private const val MAIN = "main"
        private const val HISTORY = "history"
        private const val PROFILE = "profile"
    }

    private val homeFragment = HomeComposeFragment()
    private val historyFragment = HistoryFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            if (savedInstanceState == null) {
                toggleFragment(homeFragment, MAIN)
            }
            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.main_page -> {
                        toggleFragment(homeFragment, MAIN)
                        return@setOnItemSelectedListener true
                    }
                    R.id.history_page -> {
                        toggleFragment(historyFragment, HISTORY)
                        return@setOnItemSelectedListener true
                    }
                    R.id.profile_page -> {
                        toggleFragment(profileFragment, PROFILE)
                        return@setOnItemSelectedListener true
                    }
                }
                false
            }
        }
    }

    private fun toggleFragment(fragment: Fragment, tagFragmentName: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val currentFragment = fragmentManager.primaryNavigationFragment
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment)
        }
        var fragmentTemp = fragmentManager.findFragmentByTag(tagFragmentName)
        if (fragmentTemp == null) {
            fragmentTemp = fragment
            fragmentTransaction.add(binding.viewPager.id, fragmentTemp, tagFragmentName)
        } else {
            fragmentTransaction.show(fragmentTemp)
        }
        if (!isFinishing) {
            fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp)
            fragmentTransaction.setReorderingAllowed(true)
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
}