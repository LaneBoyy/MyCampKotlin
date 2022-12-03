package ru.laneboy.firsthacaton.presentation

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

class SignUpActivity : AppCompatActivity() {

    private val apiService = ApiFactory.apiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

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
                    "ljjjjjaks@gmail.com",
                    "sadlkf",
                    "sd;lfk",
                    "df",
                    "sdfsd",
                    "skkdfsd",
                    "dsfkds"
                )
                try {
                    apiService.singUp(registrationItem)
                } catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@SignUpActivity, R.string.sign_up_error_password, Toast.LENGTH_SHORT).show()
                    }
                }
            }
//            val intent = Intent(this, MainScreenActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }
}