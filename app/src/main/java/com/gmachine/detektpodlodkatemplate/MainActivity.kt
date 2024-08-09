package com.gmachine.detektpodlodkatemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = getUserById(42)?.toString()
    }

    private fun getUserById(x: Int): String {
        return ""
    }
}
