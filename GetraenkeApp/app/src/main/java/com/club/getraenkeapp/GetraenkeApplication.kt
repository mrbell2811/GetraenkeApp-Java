package com.club.getraenkeapp

import android.app.Application

/**
 * Application class for GeträkeApp3.0
 * 
 * This class serves as the entry point for the application and manages
 * global application state and initialization.
 */
class GetraenkeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Application initialization
        // Note: Database and repository will be initialized lazily when needed
    }
}