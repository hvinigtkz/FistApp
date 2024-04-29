package com.vinicius.fistapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.vinicius.fistapp.R
import com.vinicius.fistapp.databinding.FragmentHomeBinding
import com.vinicius.fistapp.databinding.FragmentPessoaDetailBinding
import com.vinicius.fistapp.service.model.Pessoa
import com.vinicius.fistapp.viewmodel.AllPessoasViewModel
import com.vinicius.fistapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime

class PessoaDetailFragment : Fragment() {

    //chamar viewmodel
    private val viewModel: PessoaViewModel by viewModels()

    //criar binding
    private var  _binding: FragmentPessoaDetailBinding? = null
    private val  binding: FragmentPessoaDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //configurar binding
        _binding = FragmentPessoaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //pegar o id da pessoa que foi enviado pela AllPessoasFragment
        //setar a pessoa para ser carregada na tela
        arguments?.let{
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        //carregar as informações da pessoa selecionada
        viewModel.pessoa.observe(viewLifecycleOwner) {pessoa ->
            binding.tvNomeDetail.text = pessoa.nome
            binding.tvIdadeDetail.text = (LocalDateTime.now().year - pessoa.idade).toString()
            binding.tvFaixaDetail.text = pessoa.faixa

            if (pessoa.sexo == "Masculino"){
                binding.imgMasculinoDetail.visibility = View.VISIBLE
                binding.imgFemininaDetail.visibility = View.GONE
            }else{
                binding.imgMasculinoDetail.visibility = View.GONE
                binding.imgFemininaDetail.visibility = View.VISIBLE

            }

        }
    }

}