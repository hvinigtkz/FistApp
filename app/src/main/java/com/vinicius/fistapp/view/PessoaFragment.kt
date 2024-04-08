package com.vinicius.fistapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vinicius.fistapp.R
import com.vinicius.fistapp.databinding.FragmentCalculoBinding
import com.vinicius.fistapp.service.model.Pessoa
import com.vinicius.fistapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime


class PessoaFragment : Fragment() {

    private val viewModel: PessoaViewModel by viewModels()

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
            var anoNascimento = binding.edtidade.editableText.toString()


            if (nome != "" && anoNascimento != ""){
                binding.tvNome.text ="Nome: " + nome


                var anoAtual = LocalDateTime.now().year
                var idade = anoAtual - anoNascimento.toInt()

                binding.tvidade.text = "Idade: ${idade}"

                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade

                )

                viewModel.insert(pessoa)

                binding.btnVoltar2.setOnClickListener{
                    findNavController().navigate(R.id.homeFragment)



                }
            }else{
                Toast.makeText(requireContext(), "Preencha as informações", Toast.LENGTH_LONG).show()
            }
            }




    }

}