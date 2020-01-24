package ir.fallahpoor.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeBroadcastReceiver : BroadcastReceiver {

    // Every time a broadcast is received a new instance is created.
    constructor() {
        Logger.logMessage(AirplaneModeBroadcastReceiver::class.java.simpleName + " is instantiated")
    }

    // - onReceive by default is run on main thread so do NOT execute long running operations
    //   in this method (there is a 10 seconds timeout before considering the receiver a blocked
    //   one and a candidate to be killed).
    // - Class instance is destroyed as soon as onReceive's execution is done.
    override fun onReceive(context: Context?, intent: Intent?) {

        // Prevent malicious apps from sending a fake "AIRPLANE_MODE_CHANGED" broadcast
        if (intent?.action != Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            return
        }

        val isAirplaneModeOn = intent.getBooleanExtra("state", false)
        val onOff = if (isAirplaneModeOn) "ON" else "OFF"

        Logger.logMessage("Airplane mode is $onOff")

    }

}