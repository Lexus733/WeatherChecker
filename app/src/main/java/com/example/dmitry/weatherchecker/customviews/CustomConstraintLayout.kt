package com.example.dmitry.weatherchecker.customviews

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.view.GestureDetectorCompat
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent

class CustomConstraintLayout(context: Context, attributeSet: AttributeSet):ConstraintLayout(context,attributeSet), GestureDetector.OnGestureListener {

    private val  SWIPE_MIN_DISTANCE = 120
    private val SWIPE_THRESHOLD_VELOCITY = 150

    override fun onShowPress(p0: MotionEvent?) {

    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {

        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
    }

}