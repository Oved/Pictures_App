package com.example.pictures.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.example.pictures.R
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        doAnimation()
        //todo: Se crea el splash con un tiempo de espera
        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun doAnimation() {
        val animation1 = AnimationUtils.loadAnimation(this, R.anim.move_up)
        val animation2 = AnimationUtils.loadAnimation(this, R.anim.move_bottom)
        findViewById<ImageView>(R.id.img_splash).animation = animation2
        findViewById<ImageView>(R.id.img_phone).animation = animation1
    }

    override fun onPause() {
        super.onPause()
        lifecycleScope.cancel()
    }

}