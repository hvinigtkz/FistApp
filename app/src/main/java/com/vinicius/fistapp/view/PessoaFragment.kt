package com.vinicius.fistapp.view

import android.app.AlertDialog
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

        //carregar a pessoa caso tenha selecionado
        arguments?.let{
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        binding.btnDeletar.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Exclusão de pessoa")
                .setMessage("Você deseja excluir")
                .setPositiveButton("Sim"){ _,_  ->
                    viewModel.delete(viewModel.pessoa.value?.id ?: 0)
                    findNavController().navigateUp()
                }
                .setNegativeButton("Não"){_,_ ->}
                .show()
        }

        binding.btnenviar2.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var anoNascimento = binding.edtidade.editableText.toString()




            if (nome != "" && anoNascimento != "" && binding.btnMasculino.isChecked || binding.btnFeminino.isChecked) {


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




                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade,
                    sexo = sexo,
                    faixa = faixa
                )

                viewModel.pessoa.value?.let{
                    pessoa.id = it.id
                    viewModel.update(pessoa)
                } ?: run {
                    viewModel.insert(pessoa)
                }


                binding.edtNome.editableText.clear()
                binding.edtidade.editableText.clear()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Preencha as informações", Toast.LENGTH_LONG)
                    .show()
            }
        }

        viewModel.pessoa.observe(viewLifecycleOwner) {pessoa ->
            binding.edtNome.setText(pessoa.nome)
            binding.edtidade.setText((LocalDateTime.now().year - pessoa.idade).toString())

            if (pessoa.sexo == "Masculino"){
                binding.btnMasculino.isChecked = true
            } else{
                binding.btnFeminino.isChecked = true
            }
            binding.btnDeletar.visibility = View.VISIBLE
        }
    }
}