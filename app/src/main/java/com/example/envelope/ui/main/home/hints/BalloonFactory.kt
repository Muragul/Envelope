package com.example.envelope.ui.main.home.hints

import android.content.Context
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.envelope.R
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.overlay.BalloonOverlayAnimation

class BalloonFactory(private val view: View, private val hintText: String, private val x: Int) :
    Balloon.Factory() {
    override fun create(context: Context, lifecycle: LifecycleOwner?): Balloon {
        val vm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        vm.defaultDisplay.getMetrics(metrics)
        val tooltipWidth = metrics.widthPixels
        val arrowLocation: Double =
            x.toDouble() / (tooltipWidth.toDouble() - 60.toDouble())
        return createBalloon(context) {
            setArrowSize(10)
            setWidth(tooltipWidth)
            setHeight(BalloonSizeSpec.WRAP)
            setArrowPosition(arrowLocation.toFloat())
            setArrowSize(20)
            setArrowDrawable(ContextCompat.getDrawable(view.context, R.drawable.tool_tip_arrow))
            setCornerRadius(4f)
            setText(hintText)
            setTextSize(14f)
            setTextGravity(Gravity.START)
            setTextColorResource(R.color.black)
            setPadding(12)
            setMarginTop(0)
            setMarginHorizontal(15)
            setBackgroundColorResource(R.color.light_blue)
            setBalloonAnimation(BalloonAnimation.FADE)
            setLifecycleOwner(lifecycleOwner)
            setIsVisibleOverlay(true) // sets the visibility of the overlay for highlighting an anchor.
            setOverlayColorResource(R.color.overlay) // background color of the overlay using a color resource.
            setOverlayPadding(6f) // sets a padding value of the overlay shape internally.
            setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE) // default is fade.
            setDismissWhenOverlayClicked(false) // disable dismissing the balloon when the overlay is clicked.
        }
    }
}