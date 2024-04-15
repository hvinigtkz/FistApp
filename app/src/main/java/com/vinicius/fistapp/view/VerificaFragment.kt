package com.vinicius.fistapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.vinicius.fistapp.R
import com.vinicius.fistapp.databinding.FragmentVerificaBinding

class VerificaFragment : Fragment() {

    private var _binding: FragmentVerificaBinding? = null
    private val binding: FragmentVerificaBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVerificaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnviar.setOnClickListener {
            val email = binding.edtEmail.editableText.toString()

            if (email.contains("@") && email.substringAfter("@").contains(".com")) {
                binding.tvEmail.text = "Email: ${email}"
            } else {
                binding.tvEmail.text = "Email: Error"
            }

            val telefone = binding.edtTelefone.editableText.toString()

            if (telefone.length == 11) {
                binding.tvTelefone.text = "Telefone: ${telefone}"
            }
        }

    }
}