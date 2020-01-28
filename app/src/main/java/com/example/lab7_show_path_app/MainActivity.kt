package com.example.lab7_show_path_app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainAct = this
        val filter = IntentFilter("com.example.lab7_start_service_app.IMAGE_DOWNLOAD_COMPLETE")
        this.registerReceiver(MyBroadcastReceiver(), filter)

        //при программной регистрации мы можем также снять регистрацию когда не нуждаемся в приемнике
        //с помощью unregisterBroadcastReceiver()
    }

    fun updatePathTextView(path: String?) {
        if (path != null) {
            textview_for_path.text = path
        }
    }

    class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.i("MyBroadCastReceiver", "broadcast received with path: ${intent?.getStringExtra("PATH_TO_IMAGE")}")
            if (intent != null) {
                try {
                    mainAct.updatePathTextView(intent.getStringExtra("PATH_TO_IMAGE"))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    companion object {
        private lateinit var mainAct: MainActivity
    }
}
