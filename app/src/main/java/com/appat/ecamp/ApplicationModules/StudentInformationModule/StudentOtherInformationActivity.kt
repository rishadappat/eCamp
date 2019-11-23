package com.appat.ecamp.ApplicationModules.StudentInformationModule

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.appat.ecamp.ApplicationCore.BaseActivity
import com.appat.ecamp.Dialogs.LookUpDialogFragment
import com.appat.ecamp.GenericModels.LookUp
import com.appat.ecamp.R
import com.appat.ecamp.Utilities.Constants
import com.appat.ecamp.Utilities.Utility
import com.appat.ecamp.Utilities.getDate
import kotlinx.android.synthetic.main.activity_student_other_information.*
import java.util.*

class StudentOtherInformationActivity : BaseActivity() {

    lateinit var datePickerDialog: DatePickerDialog
    private lateinit var passportExpiryDate: Date
    private lateinit var studentIDExpiryDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_other_information)

        editTextStudentPassportExpiry.setOnClickListener {
            openCalendarForPassportExpiryDate()
        }

        editTextStudentIDExpiry.setOnClickListener {
            openCalendarForStudentIDExpiry()
        }

        editTextBloodGroup.setOnClickListener {
            openBloodGroups()
        }

        proceedButton.setOnClickListener {
            val intent = Intent(this, StudentAttachmentUploadActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openCalendarForPassportExpiryDate()
    {
        datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            Log.i("SelectedDate: ", view.getDate().toString())
            editTextStudentPassportExpiry.setText(Utility.dateToString(view.getDate(), Constants.DATE_FORMAT_ddMMyyyy))
            datePickerDialog.dismiss()
            passportExpiryDate = view.getDate()
        }, Utility.getYearFromDate(Date()), Utility.getMonthFromDate(Date()), Utility.getDAYFromDate(Date()))

        datePickerDialog.show()
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Utility.getColor(this, R.color.colorPrimary))
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Utility.getColor(this, R.color.colorPrimary))
    }

    private fun openCalendarForStudentIDExpiry()
    {
        datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            Log.i("SelectedDate: ", view.getDate().toString())
            editTextStudentIDExpiry.setText(Utility.dateToString(view.getDate(), Constants.DATE_FORMAT_ddMMyyyy))
            datePickerDialog.dismiss()
            studentIDExpiryDate = view.getDate()
        }, Utility.getYearFromDate(Date()), Utility.getMonthFromDate(Date()), Utility.getDAYFromDate(Date()))

        datePickerDialog.show()
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Utility.getColor(this, R.color.colorPrimary))
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Utility.getColor(this, R.color.colorPrimary))
    }

    private fun openBloodGroups()
    {
        val bloodGroups = arrayOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")

        val bloodGroupsArray = ArrayList<LookUp>()
        for((index, bloodGroup) in bloodGroups.withIndex())
        {
            val lookUp = LookUp(index.toString(), bloodGroup, bloodGroup)
            bloodGroupsArray.add(lookUp)
        }

        val lookupDialog = LookUpDialogFragment.newInstance(Utility.getString(R.string.blood_group, this), bloodGroupsArray, 2)
        lookupDialog.show(supportFragmentManager, this.javaClass.name)
        lookupDialog.lookUpItemSelected {
            editTextBloodGroup.setText(it.nameEn)
        }
    }
}
