package ru.laneboy.firsthacaton.presentation

import android.os.Bundle
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
    }

    private fun clickOnBtnSignUp() {
        findViewById<AppCompatButton>(R.id.btnStart).setOnClickListener {
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
                        Toast.makeText(this@SignUpActivity, "Алло, пароли чекни, почему они разные?", Toast.LENGTH_SHORT).show()
                    }
                }
            }
//            val intent = Intent(this, MainScreenActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }
}