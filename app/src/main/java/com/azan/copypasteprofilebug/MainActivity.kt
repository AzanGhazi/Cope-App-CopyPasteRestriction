package com.azan.copypasteprofilebug


import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.os.Bundle
import android.os.UserManager.DISALLOW_CROSS_PROFILE_COPY_PASTE
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private val adminComponent by lazy { ComponentName(this, DeviceAdmin::class.java) }
    private val devicePolicyManager by lazy { getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager }
    private val parentDevicePolicyManager by lazy { devicePolicyManager.getParentProfileInstance(adminComponent) }
    private val setCopyPasteRestrictionButton: View by lazy { findViewById(R.id.set_copy_paste_restriction) }
    private val clearCopyPasteRestrictionButton: View by lazy { findViewById(R.id.get_copy_paste_restriction) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setCopyPasteRestrictionButton.setOnClickListener(this::setCopyPasteRestriction)
        clearCopyPasteRestrictionButton.setOnClickListener(this::clearCopyPasteRestriction)
    }

    private fun setCopyPasteRestriction(view: View?) {

        setCopyPasteRestrictions();
    }

    private fun clearCopyPasteRestriction(view: View?) {
        removeCopyPasteRestrictions()
    }

    private fun setCopyPasteRestrictions() {
        try {
            devicePolicyManager.addUserRestriction(adminComponent, DISALLOW_CROSS_PROFILE_COPY_PASTE)
            parentDevicePolicyManager.addUserRestriction(adminComponent, DISALLOW_CROSS_PROFILE_COPY_PASTE)
        } catch (e: SecurityException) {
            Log.e("BugDemo", "Exception found during setting restriction");
        }

    }

    private fun removeCopyPasteRestrictions() {

        try {
            devicePolicyManager.clearUserRestriction(adminComponent, DISALLOW_CROSS_PROFILE_COPY_PASTE)
            parentDevicePolicyManager.clearUserRestriction(adminComponent, DISALLOW_CROSS_PROFILE_COPY_PASTE)
        } catch (e: Exception) {
            Log.e("BugDemo", "Exception found during removing restriction");
        }
    }
}