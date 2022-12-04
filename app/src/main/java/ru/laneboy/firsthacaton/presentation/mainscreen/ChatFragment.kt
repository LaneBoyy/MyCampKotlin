package ru.laneboy.firsthacaton.presentation.mainscreen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.laneboy.firsthacaton.MainViewModel
import ru.laneboy.firsthacaton.R
import ru.laneboy.firsthacaton.databinding.FragmentCampListBinding
import ru.laneboy.firsthacaton.databinding.FragmentChatBinding
import ru.laneboy.firsthacaton.presentation.SignUpActivity


class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding: FragmentChatBinding
        get() = _binding?: throw RuntimeException("FragmentChatBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
    }

    private fun setOnClickListener(){
        binding.acBtnGoToSignUp.setOnClickListener {
            val intent = Intent(requireActivity(), SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = ChatFragment()
    }
}