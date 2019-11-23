package com.appat.ecamp.ApplicationModules.UserManagement

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.appat.ecamp.ApplicationModules.Dashboard.Activities.MainBottomBarActivity
import com.appat.ecamp.R
import com.appat.ecamp.Utilities.AppPreferences
import com.appat.ecamp.ApplicationCore.BaseActivity
import com.appat.ecamp.Utilities.Utility
import com.google.android.material.textfield.TextInputEditText
import org.jetbrains.anko.find

class ParentLoginActivity : BaseActivity() {

    private lateinit var editTextUsername: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_login)
        editTextUsername = find(R.id.editTextUsername)
        editTextPassword = find(R.id.editTextPassword)
        loginButton = find(R.id.loginButton)

        loginButton.setOnClickListener {
            performLogin()
        }
    }
    private fun performLogin()
    {
        if(editTextUsername.text!!.isEmpty()) {
            Utility.showFlashBar(this, Utility.getString(R.string.media, this))
        }
        else if(editTextPassword.text!!.isEmpty()) {
            Utility.showFlashBar(this, Utility.getString(R.string.media, this))
        }
        else
        {
            AppPreferences.setIsUserLoggedIn(true)
            val intent = Intent(this, MainBottomBarActivity::class.java)
            startActivity(intent)
        }
    }
}
