package com.example.dmitry.weatherchecker.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class GraphViewDemo(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var paint: Paint = Paint()
    private var paint2: Paint = Paint()
    private var paint3: Paint = Paint()
    private var path: Path = Path()
    private var data = floatArrayOf(50F, -40F, 60F, -50F, 70F, -60F, 80F, -70F, 90F, -100F)
    private var heightM = 0
    private var widthM = 0
    private var heightP = 0
    private var widthP = 0
    private var size = 0
    private var widthPoint = 0
    private var iterator = 1

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        heightM = View.MeasureSpec.getSize(heightMeasureSpec)
        widthM = View.MeasureSpec.getSize(widthMeasureSpec)
        heightP = heightM - paddingTop - paddingBottom
        widthP = widthM - paddingLeft - paddingRight
        widthPoint = widthP / data.size
        size = widthPoint
    }

    override fun onDraw(canvas: Canvas?) {
        TimeUnit.SECONDS.sleep(1)
        paint.strokeWidth = 2F
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.isAntiAlias = true

        paint2.strokeWidth = 3F
        paint2.color = Color.BLACK
        paint2.textAlign = Paint.Align.RIGHT

        paint3.strokeWidth = 3F
        paint3.color = Color.BLACK
        paint3.textAlign = Paint.Align.CENTER
        paint3.textSize = 16F


        // X
        canvas?.drawLine(size.toFloat(), heightP.toFloat() / heightP.toFloat(), size.toFloat(), heightP.toFloat(), paint2)
        // Y
        canvas?.drawLine(size.toFloat(), heightP.toFloat(), widthP.toFloat(), heightP.toFloat(), paint2)

        canvas?.drawText("Temp",30F,50F,paint3)

        path.moveTo(size.toFloat(),0F)

        for (i in data) {
            if (i < -20) {
                path.lineTo((size).toFloat(), heightP - (heightP / abs(i + data.size) * data.size) / 2F)
                canvas?.drawText("$i", (size).toFloat(), heightP - (heightP / abs(i + data.size) * data.size) / 2F, paint2)
              //  canvas?.drawText("$i", widthPoint.toFloat(), heightP - (heightP / abs(i + data.size) * data.size) / 2F, paint2)
            }
            if (i > 0) {
                path.lineTo((size).toFloat(), heightP / (i + data.size) * data.size)
                canvas?.drawText("$i", (size).toFloat(), heightP / (i + data.size) * data.size, paint2)
               // canvas?.drawText("$i", widthPoint.toFloat(), heightP / (i + data.size) * data.size, paint2)
            }
            if (i in -20..0) {
                path.lineTo((size).toFloat(), heightP - (heightP / abs(i + data.size) * data.size) / 8F)
                canvas?.drawText("$i", (size).toFloat(), heightP - (heightP / abs(i + data.size) * data.size) / 8F, paint2)
               // canvas?.drawText("$i", widthPoint.toFloat(), heightP - (heightP / abs(i + data.size) * data.size) / 8F, paint2)

            }
            canvas?.drawText("$iterator", size.toFloat(), heightM.toFloat(), paint3)
            iterator++
            size += widthPoint
            canvas?.drawPath(path, paint)
        }

    }

    fun setData(data: FloatArray) {
        this.data = data
    }
}