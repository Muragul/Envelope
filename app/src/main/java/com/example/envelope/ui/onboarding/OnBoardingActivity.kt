package com.example.envelope.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import androidx.viewpager2.widget.ViewPager2
import com.example.envelope.databinding.ActivityOnBoardingBinding
import com.example.envelope.ui.start.StartActivity
import com.example.envelope.utils.binding.BindingActivity
import com.example.envelope.utils.extensions.hide
import com.example.envelope.utils.extensions.show
import com.example.envelope.utils.onBoardingList

class OnBoardingActivity :
    BindingActivity<ActivityOnBoardingBinding>(ActivityOnBoardingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            val adapter = OnBoardingAdapter()
            val onBoardingList = onBoardingList
            adapter.submitList(onBoardingList)
            vpOnBoarding.adapter = adapter
            dotsIndicator.setViewPager2(vpOnBoarding)

            vpOnBoarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (position == onBoardingList.size - 1) {
                        TransitionManager.beginDelayedTransition(
                            ltContainer, TransitionSet()
                                .addTransition(ChangeBounds())
                                .addTransition(Fade())
                        )
                        btnSkip.hide()
                        btnNext.hide()
                        btnStart.show()
                    } else if (position == onBoardingList.size - 2) {
                        TransitionManager.beginDelayedTransition(
                            ltContainer, TransitionSet()
                                .addTransition(ChangeBounds())
                                .addTransition(Fade())
                        )
                        btnNext.show()
                        btnSkip.show()
                        btnStart.hide()
                    }
                }
            })

            btnSkip.setOnClickListener {
                startActivity(Intent(this@OnBoardingActivity, StartActivity::class.java))
                finish()
            }

            btnStart.setOnClickListener {
                startActivity(Intent(this@OnBoardingActivity, StartActivity::class.java))
                finish()
            }

            btnNext.setOnClickListener {
                if (vpOnBoarding.currentItem < onBoardingList.size - 1) {
                    vpOnBoarding.currentItem++
                } else {
                    startActivity(Intent(this@OnBoardingActivity, StartActivity::class.java))
                }
            }

        }
    }
}