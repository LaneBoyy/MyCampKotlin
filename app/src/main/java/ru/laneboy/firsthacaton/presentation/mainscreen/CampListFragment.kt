package ru.laneboy.firsthacaton.presentation.mainscreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.laneboy.firsthacaton.MainViewModel
import ru.laneboy.firsthacaton.databinding.FragmentCampListBinding
import ru.laneboy.firsthacaton.presentation.CampItemActivity
import ru.laneboy.firsthacaton.presentation.CampListAdapter

class CampListFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private var campListAdapter: CampListAdapter? = null

    private var _binding: FragmentCampListBinding? = null
    private val binding: FragmentCampListBinding
    get() = _binding?: throw RuntimeException("FragmentCampListBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCampListBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillRecyclerView()
        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fillRecyclerView(){
        viewModel.campList.observe(this) {
            campListAdapter?.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvCampList) {
            campListAdapter = CampListAdapter()
            adapter = campListAdapter
        }
        setupClickListener()
    }

    private fun setupClickListener() {
         campListAdapter?.onCampItemClickListener = {
            val intent = Intent(requireActivity(), CampItemActivity::class.java)
            intent.putExtra(INTENT_NAME_CAMP, it.nameCamp)
            intent.putExtra(INTENT_TYPE_CAMP, it.typeCamp)
            intent.putExtra(INTENT_CITY_OF_CAMP, it.cityForCamp)
            intent.putExtra(INTENT_COAST_CAMP, it.coastOfCamp)
            intent.putExtra(INTENT_PICTURE_CAMP, it.pictureCamp)
            startActivity(intent)
        }
    }

    companion object {

        fun newInstance() = CampListFragment()

        const val INTENT_NAME_CAMP = "NAME"
        const val INTENT_TYPE_CAMP = "TYPE"
        const val INTENT_CITY_OF_CAMP = "CITY"
        const val INTENT_COAST_CAMP = "COAST"
        const val INTENT_PICTURE_CAMP = "PICTURE"
    }
}