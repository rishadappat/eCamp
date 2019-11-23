package com.appat.ecamp.ApplicationModules.StudentInformationModule

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.appat.ecamp.Dialogs.LookUpDialogFragment
import com.appat.ecamp.GenericModels.LookUp
import com.appat.ecamp.R
import com.appat.ecamp.ApplicationCore.BaseActivity
import com.appat.ecamp.Utilities.Constants.Companion.DATE_FORMAT_ddMMyyyy
import com.appat.ecamp.Utilities.Utility
import com.appat.ecamp.Utilities.Utility.getDAYFromDate
import com.appat.ecamp.Utilities.Utility.getMonthFromDate
import com.appat.ecamp.Utilities.Utility.getYearFromDate
import com.appat.ecamp.Utilities.getDate
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_main_bottom_bar.appBarLayout
import kotlinx.android.synthetic.main.activity_main_bottom_bar.collapsingToolbar
import kotlinx.android.synthetic.main.activity_main_bottom_bar.toolbar
import kotlinx.android.synthetic.main.activity_student_information_form.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs


class StudentInformationFormActivity : BaseActivity(), AppBarLayout.OnOffsetChangedListener {

    lateinit var datePickerDialog: DatePickerDialog
    private lateinit var selectedDob: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_information_form)
        appBarLayout.addOnOffsetChangedListener(this)
        collapsingToolbar.title = getString(R.string.student_registration)

        proceedButton.setOnClickListener {
            val intent = Intent(this, StudentContactInformationActivity::class.java)
            startActivity(intent)
        }
        editTextDoB.setOnClickListener {
            openCalendar()
        }
        editTextCenter.setOnClickListener {
            openCenters()
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val percentage = abs(verticalOffset).toFloat() / appBarLayout!!.totalScrollRange
        toolbar.alpha = percentage
    }

    private fun openCalendar()
    {
        datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            Log.i("SelectedDate: ", view.getDate().toString())
            editTextDoB.setText(Utility.dateToString(view.getDate(), DATE_FORMAT_ddMMyyyy))
            datePickerDialog.dismiss()
            selectedDob = view.getDate()
        }, getYearFromDate(Date()), getMonthFromDate(Date()), getDAYFromDate(Date()))

        datePickerDialog.show()
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Utility.getColor(this, R.color.colorPrimary))
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Utility.getColor(this, R.color.colorPrimary))
    }

    private fun openCenters()
    {
        val centers = arrayOf("Abu Dhabi Center", "Abu Dhabi Women Center", "Al Samaha Men Center",
            "Al Samaha Women Center", "Al Wathba Center", "Sweihan Center", "Al Ain Center")

        val centersArray = ArrayList<LookUp>()
        for((index, center) in centers.withIndex())
        {
            val lookUp = LookUp(index.toString(), center, center)
            centersArray.add(lookUp)
        }

        val lookupDialog = LookUpDialogFragment.newInstance(Utility.getString(R.string.center, this), centersArray)
        lookupDialog.show(supportFragmentManager, this.javaClass.name)
        lookupDialog.lookUpItemSelected {
            editTextCenter.setText(it.nameEn)
        }
    }
}
