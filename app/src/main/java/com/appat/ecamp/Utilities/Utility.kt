package com.appat.ecamp.Utilities

import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.andrognito.flashbar.Flashbar
import com.andrognito.flashbar.anim.FlashAnim
import com.appat.ecamp.ApplicationCore.CoreApplication
import com.appat.ecamp.R
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    fun getContext(): Context
    {
        return CoreApplication.instance!!.applicationContext
    }

    fun getString(string: Int, context: Context): String {
        return context.getString(string)
    }

    fun showFlashBar(context: Context, message: String)
    {
        Flashbar.Builder(context as Activity)
            .gravity(Flashbar.Gravity.BOTTOM)
            .title(R.string.error)
            .message(message)
            .duration(3000)
            .backgroundColorRes(android.R.color.holo_red_dark)
            .enterAnimation(
                FlashAnim.with(context)
                    .animateBar()
                    .duration(750)
                    .alpha()
                    .overshoot())
            .exitAnimation(
                FlashAnim.with(context)
                    .animateBar()
                    .duration(400)
                    .accelerateDecelerate())
            .build().show()
    }

    fun getYearFromDate(date: Date): Int
    {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.YEAR)
    }

    fun getMonthFromDate(date: Date): Int
    {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.MONTH)
    }

    fun getDAYFromDate(date: Date): Int
    {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun getColor(conetxt:Context, color: Int): Int {
        return ContextCompat.getColor(conetxt, color)
    }

    fun dateToString(date: Date, format: String): String {
        val dateFormat = SimpleDateFormat(format, Locale("en"))
        return dateFormat.format(date)
    }
}