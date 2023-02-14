package com.example.newsapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        Handler(Looper.getMainLooper()).postDelayed({ StartActivity() }, 2000)
    }

    private fun StartActivity() {
        val intent = Intent(this , HomeActivity::class.java)
        startActivity(intent)
    }
}
