package com.appat.ecamp.ApplicationModules.StudentInformationModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Gravity
import android.view.View
import androidx.core.view.marginStart
import com.appat.ecamp.ApplicationCore.BaseActivity
import com.appat.ecamp.R
import com.bumptech.glide.Glide
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.android.synthetic.main.activity_student_attachment_upload.*

class StudentAttachmentUploadActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_attachment_upload)

        studentPhotoCardView.setOnClickListener {
            TedImagePicker.with(this)
                .title(R.string.student_photo)
                .showCameraTile(true)
                .start { uri ->
                    Glide.with(this).load(uri).centerCrop().into(studentPhoto)
                    studentPhoto.setPadding(0,0,0,0)
                    studentPhotoContainer.visibility = View.VISIBLE
                    studentPhotoLogo.visibility = View.GONE
                }
        }

        medicalReportCardView.setOnClickListener {
            TedImagePicker.with(this)
                .title(R.string.medical_report)
                .showCameraTile(true)
                .start { uri ->
                    Glide.with(this).load(uri).centerCrop().into(medicalReport)
                    medicalReport.setPadding(0,0,0,0)
                    medicalReportContainer.visibility = View.VISIBLE
                    medicalReportLogo.visibility = View.GONE
                }
        }

        emiratesIDtCardView.setOnClickListener {
            TedImagePicker.with(this)
                .title(R.string.student_emirates_id)
                .showCameraTile(true)
                .start { uri ->
                    Glide.with(this).load(uri).centerCrop().into(emiratesID)
                    emiratesID.setPadding(0,0,0,0)
                    emiratesIDContainer.visibility = View.VISIBLE
                    emiratesIDLogo.visibility = View.GONE
                }
        }

        parentConsentCardView.setOnClickListener {
            TedImagePicker.with(this)
                .title(R.string.parent_consent)
                .showCameraTile(true)
                .start { uri ->
                    Glide.with(this).load(uri).centerCrop().into(parentConsent)
                    parentConsent.setPadding(0,0,0,0)
                    parentConsentContainer.visibility = View.VISIBLE
                    parentConsentLogo.visibility = View.GONE
                }
        }

        proceedButton.setOnClickListener {
            val intent = Intent(this, StudentRegistrationSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}
