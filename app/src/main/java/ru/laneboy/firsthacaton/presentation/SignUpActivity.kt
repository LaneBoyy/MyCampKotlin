package ru.laneboy.firsthacaton.presentation

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.laneboy.firsthacaton.R
import ru.laneboy.firsthacaton.api.ApiFactory
import ru.laneboy.firsthacaton.data.SignUpDataResponse
import ru.laneboy.firsthacaton.databinding.ActivityMainScreenBinding
import ru.laneboy.firsthacaton.databinding.ActivitySignInBinding
import ru.laneboy.firsthacaton.databinding.ActivitySingUpBinding

class SignUpActivity : AppCompatActivity() {

    private val apiService = ApiFactory.apiService
    private val binding by lazy {
        ActivitySingUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        clickOnBtnSignUp()
        removeStatusBar()
    }

    private fun removeStatusBar() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun clickOnBtnSignUp() {
        findViewById<AppCompatButton>(R.id.btnSignUp).setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val registrationItem = SignUpDataResponse(
                    binding.etEmailUp.text.toString(),
                    binding.etFirstName.text.toString(),
                    binding.etLastName.text.toString(),
                    binding.etMiddleName.text.toString(),
                    binding.etPasswordUp.text.toString(),
                    binding.etPasswordConfirm.text.toString(),
                    getString(R.string.role_parent)
                )
                try {
                    apiService.singUp(registrationItem)
                    Toast.makeText(
                        this@SignUpActivity,
                        getString(R.string.sign_up_successfully),
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@SignUpActivity, MainScreenActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@SignUpActivity,
                            R.string.sign_up_error_password,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}