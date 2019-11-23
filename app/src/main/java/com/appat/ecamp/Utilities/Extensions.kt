package com.appat.ecamp.Utilities

import android.widget.DatePicker
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.util.*

fun ViewPager2.reduceDragSensitivity() {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop*20)       // "8" was obtained experimentally
}

fun DatePicker.getDate(): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)
    return calendar.time
}