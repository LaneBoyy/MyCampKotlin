package ru.laneboy.firsthacaton.presentation

import android.content.Intent
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.laneboy.firsthacaton.R


class StartScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)

        removeStatusBar()
        setTextGradient()
        clickOnSignIn()
        clickOnSignUp()
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
            startActivity(intent)
        }
    }

    private fun clickOnSignIn() {
        findViewById<LinearLayout>(R.id.llSingIn).setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
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
}