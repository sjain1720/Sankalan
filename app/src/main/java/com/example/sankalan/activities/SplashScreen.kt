package com.example.sankalan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.sankalan.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreen : AppCompatActivity() {
    private val firebaseAuth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()

        val rotationOne: Animation? = AnimationUtils.loadAnimation(this, R.anim.rotator_one)
        findViewById<ImageView>(R.id.rotator_one_iv).startAnimation(rotationOne)
        val rotationTwo: Animation? = AnimationUtils.loadAnimation(this, R.anim.rotator_two)
        findViewById<ImageView>(R.id.rotator_two_iv).startAnimation(rotationTwo)
        val rotationThree: Animation? = AnimationUtils.loadAnimation(this, R.anim.rotator_three)
        findViewById<ImageView>(R.id.rotator_three_iv).startAnimation(rotationThree)
        val rotationFour: Animation? = AnimationUtils.loadAnimation(this, R.anim.rotator_four)
        findViewById<ImageView>(R.id.rotator_four_iv).startAnimation(rotationFour)

        val handler = Handler()
        handler.postDelayed(Runnable {
            //The following code will execute after the 5 seconds.
            try {
                // CHeck if already Login or Not
                check()
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }

        }, 5000)

    }

    private fun check() {
        val currentUser = firebaseAuth.currentUser
        Log.w("firebase", "${currentUser}")
        if (currentUser != null) {
            //Already Login Go to Home Screen
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("user", currentUser)
            startActivity(i)
            this.finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }
    }
}