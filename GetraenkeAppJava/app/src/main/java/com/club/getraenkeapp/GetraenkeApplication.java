package com.club.getraenkeapp;

import android.app.Application;
import android.util.Log;

import com.club.getraenkeapp.data.database.DatabaseHelper;

/**
 * Application class for GeträkeApp3.0 Java implementation
 * 
 * This class serves as the entry point for the application and manages
 * global application state and initialization.
 */
public class GetraenkeApplication extends Application {
    
    private static final String TAG = "GetraenkeApplication";
    
    private static GetraenkeApplication instance;
    private DatabaseHelper databaseHelper;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        Log.d(TAG, "Application starting up");
        instance = this;
        
        // Initialize database helper
        databaseHelper = DatabaseHelper.getInstance(this);
        
        Log.d(TAG, "Application initialization completed");
    }
    
    /**
     * Get application instance
     */
    public static GetraenkeApplication getInstance() {
        return instance;
    }
    
    /**
     * Get database helper instance
     */
    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
}