package com.appat.ecamp.ApplicationCore

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import com.appat.ecamp.R
import com.appat.ecamp.Utilities.Utility


abstract class BaseFragmentActivity : FragmentActivity() {

    private val TAG = "BaseActivity"
    private lateinit var connectivityManager: ConnectivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        val attributes = window.attributes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        //Utility.resetActivityTitle(this)
    }

    override fun onResume() {
        super.onResume()
        registerForNetworkListener()
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
//            runOnUiThread {
//                Utility.showFlashBar(this@BaseActivity, "Connected")
//            }
        }

        override fun onLost(network: Network) {
            runOnUiThread {
                Utility.showFlashBar(
                    this@BaseFragmentActivity,
                    Utility.getString(
                        R.string.nointernet,
                        this@BaseFragmentActivity
                    )
                )
            }
//            Toast.makeText(this@BaseActivity, Utility.getString(R.string.nointernet), Toast.LENGTH_LONG).show()
        }
    }

    private fun registerForNetworkListener()
    {
        if(!::connectivityManager.isInitialized) {
            connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            connectivityManager.registerNetworkCallback(request, networkCallback)
        }
    }

    override fun onPause() {
        super.onPause()
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}