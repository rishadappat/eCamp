package com.appat.ecamp.Dialogs

import ae.gov.adpolice.ClickListeners.RecyclerItemClickListener
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appat.ecamp.GenericModels.LookUp
import com.appat.ecamp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.jetbrains.anko.find

class LookUpDialogFragment  : BottomSheetDialogFragment() {

    lateinit var titleString: String

    lateinit var title: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var lookUps: ArrayList<LookUp>
    lateinit var adapter: LookUpDialogAdapter

    private var lookUpSelected: (lookUp: LookUp) -> Unit = {  }
    private var columns = 1

    companion object {
        fun newInstance(titleString: String, lookUps: ArrayList<LookUp>): LookUpDialogFragment {
            val fragment = LookUpDialogFragment()
            fragment.titleString = titleString
            fragment.lookUps = lookUps
            return fragment
        }

        fun newInstance(titleString: String, lookUps: ArrayList<LookUp>, columns: Int): LookUpDialogFragment {
            val fragment = LookUpDialogFragment()
            fragment.titleString = titleString
            fragment.lookUps = lookUps
            fragment.columns = columns
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
    }

    override fun onStart() {
        super.onStart()
        if (dialog != null && dialog!!.window != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val window = dialog!!.window
            window!!.findViewById<FrameLayout>(com.google.android.material.R.id.container).fitsSystemWindows =
                false
            val decorView = window.decorView
            decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.look_up_dialog_fragment, container, false)
        title = view.find(R.id.title)
        recyclerView = view.find(R.id.recyclerView)

        title.text = titleString
        if(columns > 1) {
            recyclerView.layoutManager = GridLayoutManager(this.activity, columns)
        }
        else {
            recyclerView.layoutManager = LinearLayoutManager(this.activity, RecyclerView.VERTICAL, false)
        }
        adapter = LookUpDialogAdapter.lookUpsAdapter(lookUps)
        recyclerView.adapter = adapter

        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(this.activity!!, object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    lookUpSelected(lookUps[position])
                    dismiss()
                }
            })
        )
        return view
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            dialog.behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        dialog.dismiss()
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    fun lookUpItemSelected(lookUpSelected: (lookUp: LookUp) -> Unit = { })
    {
        this.lookUpSelected = lookUpSelected
    }

}