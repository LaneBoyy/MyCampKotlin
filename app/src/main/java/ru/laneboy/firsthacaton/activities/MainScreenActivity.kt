package ru.laneboy.firsthacaton.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ru.laneboy.firsthacaton.MainViewModel
import ru.laneboy.firsthacaton.R

class MainScreenActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var campListAdapter: CampListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        removeStatusBar()
        setupRecyclerView()
        fillRecyclerView()
    }

    private fun removeStatusBar() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun fillRecyclerView(){
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.campList.observe(this) {
            campListAdapter?.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rvCampList)
        with(recyclerView) {
            campListAdapter = CampListAdapter()
            adapter = campListAdapter
        }
        setupClickListener()
    }

    private fun setupClickListener() {
        campListAdapter?.onCampItemClickListener = {
            val intent = Intent(this, CampItemActivity::class.java)
            intent.putExtra(INTENT_NAME_CAMP, it.nameCamp)
            intent.putExtra(INTENT_TYPE_CAMP, it.typeCamp)
            intent.putExtra(INTENT_CITY_OF_CAMP, it.cityForCamp)
            intent.putExtra(INTENT_COAST_CAMP, it.coastOfCamp)
            intent.putExtra(INTENT_PICTURE_CAMP, it.pictureCamp)
            startActivity(intent)
        }
    }

    companion object {
        const val INTENT_NAME_CAMP = "NAME"
        const val INTENT_TYPE_CAMP = "TYPE"
        const val INTENT_CITY_OF_CAMP = "CITY"
        const val INTENT_COAST_CAMP = "COAST"
        const val INTENT_PICTURE_CAMP = "PICTURE"
    }
}