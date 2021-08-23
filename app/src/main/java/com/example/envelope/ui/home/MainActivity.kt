package com.example.envelope.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.envelope.R
import com.example.envelope.databinding.ActivityMainBinding
import com.example.envelope.ui.home.fragments.HistoryFragment
import com.example.envelope.ui.home.fragments.HomeFragment
import com.example.envelope.ui.home.fragments.ProfileFragment
import com.example.envelope.utils.binding.BindingActivity

class MainActivity : BindingActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    companion object {
        private const val MAIN = "main"
        private const val HISTORY = "history"
        private const val PROFILE = "profile"
    }

    private var fragmentList = arrayListOf(
        HomeFragment(),
        HistoryFragment(),
        ProfileFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            bottomNavigation.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.main_page -> {
                        toggleFragment(fragmentList[0], MAIN)
                        return@setOnItemSelectedListener true
                    }
                    R.id.history_page -> {
                        toggleFragment(fragmentList[1], HISTORY)
                        return@setOnItemSelectedListener true
                    }
                    R.id.profile_page -> {
                        toggleFragment(fragmentList[2], PROFILE)
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
    }
}