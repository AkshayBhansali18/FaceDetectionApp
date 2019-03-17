package com.example.mlkitproject;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class InitializeFirebase extends Application {
    public static final String resultText="resultText";
    public static final String resultDialog="resultDialog";


    @Override
    public void onCreate() {
        FirebaseApp.initializeApp(this);
        super.onCreate();
    }
}
