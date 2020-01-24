package ir.fallahpoor.broadcasts

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ACTION = "ir.fallahpoor.broadcasts.MY_BROADCAST"
        private const val KEY_MESSAGE = "key_message"
    }

    private val screenOnOffBroadcastReceiver = ScreenOnOffBroadcastReceiver()
    private val myBroadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendBroadcastButton.setOnClickListener {
            sendCustomBroadcast()
        }

        registerReceivers()

    }

    private fun sendCustomBroadcast() {

        val intent = Intent().apply {
            action = ACTION
            putExtra(KEY_MESSAGE, "Whasup?")
        }

        /**
         * I want to send a broadcast local to my app so I use LocalBroadcastManager
         * to send the broadcast instead of using Context.sendBroadcast() method.
         */
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)

    }

    private fun registerReceivers() {
        registerCustomReceiver()
        registerScreenOnOffReceiver()
    }

    private fun registerCustomReceiver() {
        val intentFilter = IntentFilter(ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(myBroadcastReceiver, intentFilter)
    }

    private fun registerScreenOnOffReceiver() {

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }

        /**
         * Context-registered receivers receive broadcasts as long as their registering
         * context is valid. For an example, if you register within an Activity context,
         * you receive broadcasts as long as the activity is not destroyed. If you register
         * with the Application context, you receive broadcasts as long as the app is running.
         */
        registerReceiver(screenOnOffBroadcastReceiver, intentFilter)

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(screenOnOffBroadcastReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myBroadcastReceiver)
    }

}
