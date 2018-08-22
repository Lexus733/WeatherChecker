package com.example.dmitry.weatherchecker.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class GraphView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val paint = Paint()
    private val path = Path()
    private var heightM: Int = 0
    private var widthM: Int = 0
    private var topM: Int = 0
    private var bottomM: Int = 0
    private var rightM: Int = 0
    private var leftM: Int = 0
    private var dataPoints: FloatArray = floatArrayOf(10F, 100F, 50F, 51F, 52F, 53F, 54F, 55F, 54F, 53F, 52F, 51F, 50F, 100F, 10F)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        heightM = View.MeasureSpec.getSize(heightMeasureSpec)
        widthM = View.MeasureSpec.getSize(widthMeasureSpec)
        rightM = right
        leftM = left
        topM = top
        bottomM = bottom

    }

    override fun onDraw(canvas: Canvas) {
        //прямые линии
        paint.color = Color.GRAY
        paint.setShadowLayer(4F, 2F, 2F, Color.WHITE)

        canvas.drawLine(leftM.toFloat(), heightM / 2F, rightM.toFloat(), heightM / 2F, paint)
        canvas.drawLine(leftM.toFloat(), heightM / 4F, rightM.toFloat(), heightM / 4F, paint)
        canvas.drawLine(leftM.toFloat(), heightM - heightM / 4F, rightM.toFloat(), heightM - heightM / 4F, paint)

        //график
        paint.reset()
        paint.strokeWidth = 8f
        paint.color = Color.WHITE

        path.moveTo(getXPos(0F), getYPos(dataPoints[0]))
        for (i in 0 until dataPoints.size) {
            path.lineTo(getXPos(i.toFloat()), getYPos(dataPoints[i]))
            canvas.drawText("${dataPoints[i]}", getXPos(i.toFloat()), getYPos(dataPoints[i]), paint)
        }

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f
        paint.color = Color.WHITE
        paint.isAntiAlias = true
        paint.setShadowLayer(4F, 2F, 2F, Color.WHITE)

        canvas.drawPath(path, paint)

        //ось Y
        canvas.drawLine(getXPos(0F), getYPos(dataPoints.min()!!.toFloat()), getXPos(0F), getYPos(dataPoints.max()!!.toFloat()), paint)

        path.reset()
        paint.reset()
        paint.strokeWidth = 8f
        paint.color = Color.WHITE
        paint.textAlign = Paint.Align.RIGHT

        path.moveTo(getXPos(0F), getYPos(dataPoints[0]))
        for (i in 0 until dataPoints.size) {
            path.lineTo(getXPos(0F), getYPos(dataPoints[i]))
            canvas.drawText("${dataPoints[i]}", getXPos(0F), getYPos(dataPoints[i]), paint)
        }

        //ось X
        path.reset()
        paint.reset()
        paint.strokeWidth = 6f
        paint.color = Color.WHITE
        paint.textAlign = Paint.Align.RIGHT

        canvas.drawLine(getXPos(0F), getYPos(dataPoints.min()!!.toFloat()), getXPos(dataPoints.size.toFloat()), getYPos(dataPoints.min()!!.toFloat()), paint)

        path.moveTo(getXPos(0F), getYPos(dataPoints[0]))
        for (i in 0 until dataPoints.size) {
            path.lineTo(getXPos(i.toFloat()), getYPos(0F))
            canvas.drawText("$i", getXPos(i.toFloat()), getYPos(0F), paint)
        }
    }

    private fun getYPos(value: Float): Float {
        var value = value
        val height = (heightM - paddingTop - paddingBottom).toFloat()
        val maxValue = dataPoints.max()!!.toFloat()

        value = value / maxValue * height
        value = height - value
        value += paddingTop.toFloat()

        return value
    }

    private fun getXPos(value: Float): Float {
        var value = value
        val width = (widthM - paddingLeft - paddingRight).toFloat()
        val maxValue = dataPoints.size - 1

        value = value / maxValue * width
        value += paddingLeft.toFloat()

        return value
    }

    fun setDataPoints(dataPoints: FloatArray) {
        this.dataPoints = dataPoints
    }
}