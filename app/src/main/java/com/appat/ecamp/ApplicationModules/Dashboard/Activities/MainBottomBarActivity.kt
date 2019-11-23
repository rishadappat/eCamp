package com.appat.ecamp.ApplicationModules.Dashboard.Activities

import android.content.Intent
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.appat.ecamp.ApplicationModules.Dashboard.Adapters.MainBottomBarViewPagerAdapter
import com.appat.ecamp.R
import com.appat.ecamp.ApplicationCore.BaseFragmentActivity
import com.appat.ecamp.Utilities.reduceDragSensitivity
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main_bottom_bar.*
import kotlin.math.abs

class MainBottomBarActivity : BaseFragmentActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var adapter: MainBottomBarViewPagerAdapter

    private var isHeaderExpanded = true
    private var previousPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        setContentView(R.layout.activity_main_bottom_bar)
        appBarLayout.addOnOffsetChangedListener(this)
        adapter = MainBottomBarViewPagerAdapter(this)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        toolbar.title = getString(R.string.home)
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
        viewPager.reduceDragSensitivity()
        attachViewPagerWithBottomBar()
    }

    private fun attachViewPagerWithBottomBar() {
        bottomBar.onItemSelected = {
            viewPager.currentItem = it
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomBar.setActiveItem(position)
                when (position) {
                    0 -> {
                        collapsingToolbar.title = getString(R.string.home)
                        expandAppBar()
                    }
                    1 -> {
                        collapsingToolbar.title = getString(R.string.media)
                        collapseAppBar()
                    }
                    2 -> {
                        collapsingToolbar.title = getString(R.string.more)
                        collapseAppBar()
                    }
                }
            }
        })
    }

    private fun expandAppBar() {
        collapsingToolbar.scrimAnimationDuration = 200
        appBarLayout.setExpanded(isHeaderExpanded, true)
        previousPage = viewPager.currentItem
        enableDragInAppBarLayout(true)
        collapsingToolbar.scrimAnimationDuration = 0
    }

    private fun collapseAppBar() {
        collapsingToolbar.scrimAnimationDuration = 0
        appBarLayout.setExpanded(false, false)
        enableDragInAppBarLayout(false)
        previousPage = viewPager.currentItem
    }

    private fun enableDragInAppBarLayout(enable: Boolean)
    {
        val params = appBarLayout.layoutParams as CoordinatorLayout.LayoutParams
        if(params.behavior != null) {
            val behavior = params.behavior as AppBarLayout.Behavior
            behavior.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
                override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                    return enable
                }
            })
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val percentage = abs(verticalOffset).toFloat() / appBarLayout!!.totalScrollRange
        toolbar.alpha = percentage
    }
}
