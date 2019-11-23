package com.appat.ecamp.ApplicationModules.StudentInformationModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appat.ecamp.ApplicationCore.BaseActivity
import com.appat.ecamp.R
import kotlinx.android.synthetic.main.activity_student_other_information.*

class StudentContactInformationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_contact_information)
        proceedButton.setOnClickListener {
            val intent = Intent(this, StudentOtherInformationActivity::class.java)
            startActivity(intent)
        }
    }
}
