package com.leorigna.debug_screen_on

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.WindowManager

class DebugScreenOnPlugin: FlutterPlugin, ActivityAware, Application.ActivityLifecycleCallbacks {
  private var activity: Activity? = null

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
  }

  private fun updateScreenOnState() {
    val currentActivity = activity ?: return
    
    if (BuildConfig.DEBUG) {
      if (Debug.isDebuggerConnected() ||
          android.provider.Settings.Global.getInt(
            currentActivity.contentResolver, 
            android.provider.Settings.Global.ADB_ENABLED, 
            0
          ) == 1) {
        Log.d("SCREEN", "Keeping screen on for debugging, detach debugger and force an onResume to turn it off.")
        currentActivity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
      } else {
        currentActivity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        Log.d("SCREEN", "Keeping screen on for debugging is now deactivated.")
      }
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
    activity?.application?.registerActivityLifecycleCallbacks(this)
  }

  override fun onDetachedFromActivityForConfigChanges() {
    activity = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    activity = binding.activity
  }

  override fun onDetachedFromActivity() {
    activity?.application?.unregisterActivityLifecycleCallbacks(this)
    activity = null
  }

  override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
  override fun onActivityStarted(activity: Activity) {}
  override fun onActivityResumed(activity: Activity) {
    this.activity = activity
    updateScreenOnState()
  }
  override fun onActivityPaused(activity: Activity) {}
  override fun onActivityStopped(activity: Activity) {}
  override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
  override fun onActivityDestroyed(activity: Activity) {}
}