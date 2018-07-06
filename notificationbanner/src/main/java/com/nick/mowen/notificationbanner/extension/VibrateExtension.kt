package com.nick.mowen.notificationbanner.extension

import android.content.Context
import android.media.AudioManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

/**
 * Vibrates to notify user of action
 *
 * @param respect Whether or not to respect the device silent mode
 */
fun Context.notifyVibrate() {
    val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

    if (audioManager.checkSilent()) {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            @Suppress("DEPRECATION")
            vibrator.vibrate(longArrayOf(0, 100), -1)
        } else
            vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(0, 100), -1))
    }
}

/**
 * Calculates if vibration should be silenced or not
 *
 * @param respect whether or not to respect the system silent mode
 * @return to vibrate or not to vibrate
 */
fun AudioManager.checkSilent(): Boolean =
        ringerMode != AudioManager.RINGER_MODE_SILENT