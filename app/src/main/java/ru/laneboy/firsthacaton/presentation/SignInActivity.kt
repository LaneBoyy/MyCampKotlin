package ru.laneboy.firsthacaton.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.laneboy.firsthacaton.R
import ru.laneboy.firsthacaton.api.ApiFactory.apiService
import ru.laneboy.firsthacaton.data.SignInDataResponse
import ru.laneboy.firsthacaton.data.SignUpDataResponse
import ru.laneboy.firsthacaton.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        clickOnBtnSignIn()
        removeStatusBar()
    }

    private fun removeStatusBar() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun clickOnBtnSignIn() {
        findViewById<AppCompatButton>(R.id.btnSignIn).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val signInItem = SignInDataResponse(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString(),
                    getString(R.string.role_parent)
                )
                try {
                    apiService.signIn(signInItem)
                    val intent = Intent(this@SignInActivity, MainScreenActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@SignInActivity,
                            getString(R.string.error_sign_in),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
