package ae.gov.adpolice.ClickListeners

import android.content.Context
import android.view.MotionEvent
import android.view.GestureDetector
import android.view.View
import android.text.method.Touch.onTouchEvent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView


class RecyclerItemClickListener(context: Context, private val mListener: OnItemClickListener?) : RecyclerView.OnItemTouchListener {
    private var childView: View? = null
    private var mGestureDetector: GestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }
    })

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        if(e.action != MotionEvent.ACTION_UP) {
            childView = view.findChildViewUnder(e.x, e.y)
        }
//        if (childView != null) {
//            when (e.action) {
//                MotionEvent.ACTION_DOWN -> childView!!.animate().scaleX(0.95f).scaleY(0.95f).setDuration(200).start()
//                MotionEvent.ACTION_UP -> {
//                    childView!!.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
//                }
//                MotionEvent.ACTION_CANCEL -> {
//                    childView!!.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
//                }
//            }
//            Log.i("RecyclerViewAdapter", e.action.toString())
//        }
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView!!, view.getChildAdapterPosition(childView!!))
            return true
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }
}