package com.smartsecurity.pad

import android.app.Application
import com.google.firebase.FirebaseApp

class SmartSecurityPadApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}