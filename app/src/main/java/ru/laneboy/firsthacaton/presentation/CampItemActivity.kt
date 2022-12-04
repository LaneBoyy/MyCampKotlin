package ru.laneboy.firsthacaton.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import ru.laneboy.firsthacaton.R
import ru.laneboy.firsthacaton.presentation.mainscreen.CampListFragment
import ru.laneboy.firsthacaton.presentation.mainscreen.CampListFragment.Companion.INTENT_CITY_OF_CAMP
import ru.laneboy.firsthacaton.presentation.mainscreen.CampListFragment.Companion.INTENT_COAST_CAMP
import ru.laneboy.firsthacaton.presentation.mainscreen.CampListFragment.Companion.INTENT_NAME_CAMP
import ru.laneboy.firsthacaton.presentation.mainscreen.CampListFragment.Companion.INTENT_TYPE_CAMP

class CampItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camp_item)

        removeStatusBar()
        setTextFromIntent()
    }

    private fun removeStatusBar() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun setTextFromIntent(){
        findViewById<TextView>(R.id.tvNameCampItem).text = intent.getStringExtra(INTENT_NAME_CAMP)
        findViewById<TextView>(R.id.tvTypeCampItem).text = intent.getStringExtra(INTENT_TYPE_CAMP)
        findViewById<TextView>(R.id.tvCityCampItem).text = intent.getStringExtra(INTENT_CITY_OF_CAMP)
        findViewById<TextView>(R.id.tvCoastOfCampItem).text = intent.getStringExtra(INTENT_COAST_CAMP)
    }
}