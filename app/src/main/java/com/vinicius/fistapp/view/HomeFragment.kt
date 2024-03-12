package com.vinicius.fistapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vinicius.fistapp.R
import com.vinicius.fistapp.databinding.FragmentCalculoBinding
import com.vinicius.fistapp.databinding.FragmentHomeBinding
import com.vinicius.fistapp.databinding.FragmentVerificaBinding

class HomeFragment : Fragment() {

    private var  _binding: FragmentHomeBinding? = null
    private val  binding: FragmentHomeBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCalculo.setOnClickListener{
            findNavController().navigate(R.id.calculoFragment2)
        }

        binding.btnVerifica.setOnClickListener{
            findNavController().navigate(R.id.verificaFragment2)
        }

    }
}


