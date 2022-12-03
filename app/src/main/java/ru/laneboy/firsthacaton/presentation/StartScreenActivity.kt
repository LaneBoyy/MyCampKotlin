package ru.laneboy.firsthacaton.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.laneboy.firsthacaton.R
import ru.laneboy.firsthacaton.presentation.mainscreen.CampsOnMapFragment


class StartScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)

        removeStatusBar()
        requestLocationPermission()
        setTextGradient()
        clickOnSignIn()
        clickOnSignUp()
        clickOnWithoutSignUp()
    }

    private fun removeStatusBar() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun clickOnSignUp() {
        findViewById<LinearLayout>(R.id.llSignUp).setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            intent.putExtra(START_INTENT_KEY, INTENT_SIGN)
            startActivity(intent)
        }
    }

    private fun clickOnSignIn() {
        findViewById<LinearLayout>(R.id.llSingIn).setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra(START_INTENT_KEY, INTENT_SIGN)
            startActivity(intent)
        }
    }

    private fun clickOnWithoutSignUp() {
        findViewById<TextView>(R.id.tvWithoutSingUp).setOnClickListener {
            val intent = Intent(this, MainScreenActivity::class.java)
            intent.putExtra(START_INTENT_KEY, INTENT_SIGN_NOT)
            startActivity(intent)
        }
    }

    private fun setTextGradient() {
        val textView = findViewById<TextView>(R.id.tvChooseAction)

        val paint = textView.paint
        val width = paint.measureText(textView.text.toString())

        val textShader: Shader = LinearGradient(
            0f, 0f, width, textView.textSize,
            intArrayOf(
                Color.parseColor("#5F2C82"),
                Color.parseColor("#49A09D")
            ),
            null,
            TileMode.CLAMP
        )

        textView.paint.shader = textShader
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                "android.permission.ACCESS_FINE_LOCATION"
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf("android.permission.ACCESS_FINE_LOCATION"),
                CampsOnMapFragment.PERMISSIONS_REQUEST_FINE_LOCATION
            )
        }
    }

    companion object {
        const val START_INTENT_KEY = "START INTENT"
        const val INTENT_SIGN = "TRUE"
        const val INTENT_SIGN_NOT = "FALSE"
    }
}