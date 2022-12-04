package ru.laneboy.firsthacaton.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import ru.laneboy.firsthacaton.R
import ru.laneboy.firsthacaton.databinding.ActivityMainScreenBinding
import ru.laneboy.firsthacaton.presentation.mainscreen.AccountFragment
import ru.laneboy.firsthacaton.presentation.mainscreen.CampListFragment
import ru.laneboy.firsthacaton.presentation.mainscreen.ChatFragment
import ru.laneboy.firsthacaton.presentation.mainscreen.CampsOnMapFragment

class MainScreenActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainScreenBinding.inflate(layoutInflater)
    }

    private val mapFragment = CampsOnMapFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        removeStatusBar()

        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        setFragment(CampListFragment.newInstance())
        binding.bn.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homeList -> {
                    setFragment(CampListFragment.newInstance())
                }
                R.id.chats -> {
                    setFragment(ChatFragment.newInstance())
                }
                R.id.myChildren -> {
                    setFragment(mapFragment)
                }
                R.id.account -> {
                    setFragment(AccountFragment.newInstance())
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    fun startChatFragment() {
        binding.bn.selectedItemId = R.id.chats
    }



    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun removeStatusBar() {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}