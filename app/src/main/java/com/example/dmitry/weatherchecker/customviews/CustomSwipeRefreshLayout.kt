package com.example.dmitry.weatherchecker.customviews

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration

class CustomSwipeRefreshLayout(context: Context, attr: AttributeSet) : SwipeRefreshLayout(context,attr) {
    private var touchSlop: Int = 0
    private var prevX: Float = 0F
    init {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
            ev.let {
                when (ev!!.action){
                        MotionEvent.ACTION_DOWN -> {
                            prevX = MotionEvent.obtain(ev).getX()
                        }
                    MotionEvent.ACTION_MOVE -> {
                        val eventX : Float = ev.getX()
                        val xDiff = Math.abs(eventX - prevX)
                        if (xDiff > touchSlop)
                            return false
                    }
                }
            }
        return super.onInterceptTouchEvent(ev)
    }
}