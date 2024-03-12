package com.vinicius.fistapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vinicius.fistapp.R
import com.vinicius.fistapp.databinding.ActivityMainBinding
import com.vinicius.fistapp.databinding.FragmentCalculoBinding
import java.time.LocalDateTime


class CalculoFragment : Fragment() {

    private var  _binding: FragmentCalculoBinding? = null
    private val  binding: FragmentCalculoBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalculoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnenviar2.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()

            binding.tvNome.text ="Nome: " + nome

            var anoNascimento = binding.edtidade.editableText.toString()
            var anoAtual = LocalDateTime.now().year
            var idade = anoAtual - anoNascimento.toInt()

            binding.tvidade.text = "Idade: ${idade}"
        }

        binding.btnVoltar2.setOnClickListener{
            findNavController().navigate(R.id.homeFragment)
        }
    }


}