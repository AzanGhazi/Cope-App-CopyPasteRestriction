package com.azan.copypasteprofilebug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.app.admin.DevicePolicyManager
import android.app.Activity
import android.util.Log
import java.lang.ref.WeakReference

class OompProvisioningSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("DeviceEnrollmentAsOOMP", "Device Is progressing down the path")
        val result = Intent()
        result.putExtra(
            DevicePolicyManager.EXTRA_PROVISIONING_MODE,
            DevicePolicyManager.PROVISIONING_MODE_MANAGED_PROFILE
        )
        setResult(RESULT_OK, result)
        finish()
        Log.e("DeviceEnrollmentAsOOMP", "Device tried to enroll")
    }
}