package com.appat.ecamp.CustomViews

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

open class CustomTextInputLayout @JvmOverloads
/**
 * Constructor
 * @param context Context
 * @param attrs Attribute Set for view
 * @param defStyleAttr Int style from attr
 */
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextInputLayout(context, attrs, defStyleAttr) {

    private var bounds: Rect? = null
    private var recalculateMethod: Method? = null
    private var collapsingTextHelper: Any? = null

    /**
     * Companion Object
     */
    companion object {
        const val COLLAPSING_HELPER = "collapsingTextHelper"
        const val COLLAPSED_BOUNDS = "collapsedBounds"
        const val RECALCULATE = "recalculate"
    }

    /**
     * Init constructors
     */
    init {
        init()
    }

    /**
     * On layout changes
     * @param changed Boolean
     * @param left Int
     * @param top Int
     * @param right Int
     * @param bottom Int
     */
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        adjustBounds()
    }

    /**
     * Init function call the TextInputLayout class and the secondary internal class CollapsingTextHelper
     * @see TextInputLayout
     */
    private fun init() {
        try {
            //Search internal and private class over object called as variable
            val cthField = TextInputLayout::class.java.getDeclaredField(COLLAPSING_HELPER)
            cthField.isAccessible = true
            collapsingTextHelper = cthField.get(this)

            //Search in private class the other component to create a view
            val boundsField = collapsingTextHelper?.javaClass?.getDeclaredField(COLLAPSED_BOUNDS)
            boundsField?.isAccessible = true
            bounds = boundsField?.get(collapsingTextHelper) as Rect
            recalculateMethod = collapsingTextHelper?.javaClass?.getDeclaredMethod(RECALCULATE)

        } catch (e: NoSuchFieldException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            collapsingTextHelper = null
            bounds = null
            recalculateMethod = null
            e.printStackTrace()
        }
    }

    /**
     * Adjust Bounds of the view for padding
     * and changes for the view
     */
    private fun adjustBounds() {
        if (collapsingTextHelper == null)
            return
        try {
            bounds?.left = editText?.left!!
            recalculateMethod?.invoke(collapsingTextHelper)
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }
}