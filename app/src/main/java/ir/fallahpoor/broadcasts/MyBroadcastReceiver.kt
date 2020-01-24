package ir.fallahpoor.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        intent?.run {
            val message = getStringExtra("key_message")
            Logger.logMessage("Received message is \"$message\"")
        }

    }

}