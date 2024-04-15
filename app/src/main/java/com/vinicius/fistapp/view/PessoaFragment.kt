package com.vinicius.fistapp.view

import  android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vinicius.fistapp.R
import com.vinicius.fistapp.databinding.FragmentPessoaBinding
import com.vinicius.fistapp.service.model.Pessoa
import com.vinicius.fistapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime


class PessoaFragment : Fragment() {

    private val viewModel: PessoaViewModel by viewModels()

    private var _binding: FragmentPessoaBinding? = null
    private val binding: FragmentPessoaBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPessoaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnenviar2.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var anoNascimento = binding.edtidade.editableText.toString()


            if (nome != "" && anoNascimento != "") {
                binding.tvNome.text = "Nome: " + nome

                var sexo = ""

                if (binding.btnMasculino.isChecked){
                    sexo = "Masculino"
                }else{
                    sexo = "Feminino"
                }


                var anoAtual = LocalDateTime.now().year
                var idade = anoAtual - anoNascimento.toInt()

                var faixa = ""

                if (idade <= 12){
                    faixa = "Infantil"
                }else if (idade <= 17){
                    faixa = "Adolescente"
                }else if (idade <= 64){
                    faixa = "Adulto"
                }else if (idade  <= 100){
                    faixa = "Idoso"
                }else{
                    faixa = "morto"
                }


                binding.tvidade.text = "Idade: ${idade}"

                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade,
                    sexo = sexo,
                    faixa = faixa


                )

                viewModel.insert(pessoa)

                binding.edtNome.editableText.clear()
                binding.edtidade.editableText.clear()
                findNavController().navigateUp()



            } else {
                Toast.makeText(requireContext(), "Preencha as informações", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}