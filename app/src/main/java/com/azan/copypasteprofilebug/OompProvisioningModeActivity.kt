package com.azan.copypasteprofilebug

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class OompProvisioningModeActivity: AppCompatActivity() {

    private val devicePolicyManager by lazy { getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager }
    private val adminComponent by lazy { ComponentName(this, DeviceAdmin::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("DeviceEnrollmentAsOOMP", "Device Is progressing down the path")
        if (devicePolicyManager.isManagedProfile(adminComponent)) {
            devicePolicyManager.setProfileEnabled(adminComponent)
        }
        finishWithIntent(intent)
        Log.e("DeviceEnrollmentAsOOMP", "Device tried to enroll")
    }

    private fun finishWithIntent(intent: Intent) {
        // Google play requires these flags to be removed to avoid intent redirection vulnerability
        intent.removeFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.removeFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        setResult(RESULT_OK, intent)
        finish()
    }
}