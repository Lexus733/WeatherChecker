package com.example.dmitry.weatherchecker.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.GestureDetectorCompat
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent

class CustomConstraintLayout(context: Context, attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet), GestureDetector.OnGestureListener {

    private val SWIPE_MIN_DISTANCE = 120
    private val SWIPE_THRESHOLD_VELOCITY = 150
    private val gestureDetectorCompat: GestureDetectorCompat = GestureDetectorCompat(context, this)
    private var swipeTomorrow: (() -> Unit)? = null
    private var swipeYesterday: (() -> Unit)? = null

    fun setSwipe(spYes: () -> Unit, spTmrw: () -> Unit) {
        swipeTomorrow = spTmrw
        swipeYesterday = spYes
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetectorCompat.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onShowPress(p0: MotionEvent?) {

    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        if (p0!!.x - p1!!.x > SWIPE_MIN_DISTANCE && Math.abs(p2) > SWIPE_THRESHOLD_VELOCITY) {
            //from right to left
            swipeYesterday!!.invoke()
            return false
        } else if (p1.x - p0.x > SWIPE_MIN_DISTANCE && Math.abs(p2) > SWIPE_THRESHOLD_VELOCITY) {
            //from left to right
            swipeTomorrow!!.invoke()
            return false
        }
        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
    }
}