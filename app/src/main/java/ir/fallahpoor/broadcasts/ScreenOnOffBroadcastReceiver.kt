package ir.fallahpoor.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ScreenOnOffBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        val isScreenOn =
            when (intent?.action) {
                Intent.ACTION_SCREEN_OFF -> {
                    "OFF"
                }
                Intent.ACTION_SCREEN_ON -> {
                    "ON"
                }
                else -> {
                    "WTF!"
                }
            }

        Logger.logMessage("Screen is turned $isScreenOn")

    }

}