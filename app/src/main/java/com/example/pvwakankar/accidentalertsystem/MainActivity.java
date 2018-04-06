package com.example.pvwakankar.accidentalertsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        SharedPreferences contacts = context.getSharedPreferences("contacts", MODE_PRIVATE);
        SharedPreferences.Editor contactEditor = contacts.edit();
        contactEditor.putString("contact-1", "Nikhil:9011866262");
        contactEditor.putString("contact-2", "a:9011866262");
        contactEditor.putString("contact-3", "b:9011866262");
        contactEditor.putString("contact-4", "c:9011866262");
        contactEditor.apply();


        String contact1 = contacts.getString("contact-5", "not set");
        String[] split = contact1.split(":");
        if(split.length >0) {
            Log.d(TAG, "Name: " + split[0] + " contact number:" + split[1]);
        } else {
            Log.d(TAG, "contact not set");
        }
    }
}
