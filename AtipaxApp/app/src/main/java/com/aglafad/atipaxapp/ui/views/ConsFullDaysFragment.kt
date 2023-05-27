package com.aglafad.atipaxapp.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aglafad.atipaxapp.R
import com.aglafad.atipaxapp.databinding.FragmentConsFullDaysBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConsFullDaysFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConsFullDaysFragment : Fragment() {
    private var _binding: FragmentConsFullDaysBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        _binding = FragmentConsFullDaysBinding.inflate(inflater, container, false)
        return binding.root
    }



}