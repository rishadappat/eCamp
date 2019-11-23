package com.appat.ecamp.ApplicationCore

import android.app.Application

class CoreApplication : Application()
{
    companion object {
        private val TAG = CoreApplication::class.java.simpleName
        var instance: CoreApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}