package com.vinicius.fistapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.fistapp.databinding.ListItemPessoaBinding
import com.vinicius.fistapp.service.model.Pessoa

class PessoaAdapter(pessoas: List<Pessoa>?, private val clicklistener: (Pessoa) -> Unit) :
    RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>() {

        //criar uma lista vazia de pessoas
        private var pessoaList: List<Pessoa> = arrayListOf()
    class PessoaViewHolder(private val binding: ListItemPessoaBinding) :
        RecyclerView.ViewHolder(binding.root) {

            // carrega as informações da pessoa na lista
        fun bind(pessoa: Pessoa, clicklistener: (Pessoa) -> Unit) {
            binding.tvNome.text = pessoa.nome
            binding.tvIdade.text = pessoa.idade.toString()
            binding.tvFaixaEtaria.text = pessoa.faixa

            if (pessoa.sexo == "Masculino"){
                binding.imgmasculino.visibility = View.VISIBLE
                binding.imgfeminina.visibility = View.GONE
            }else{
                binding.imgmasculino.visibility = View.GONE
                binding.imgfeminina.visibility = View.VISIBLE

            }

                //configura o clique de algum item da lista
            binding.root.setOnClickListener {
                clicklistener(pessoa)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        //configura o binding da lista
        val listItemPessoaBinding =
            ListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoaViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return pessoaList.count()
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(pessoaList[position], clicklistener)
    }

    //carrega a lista de pessoas para poder serem exibida
    fun updatePessoas(list: List<Pessoa>){
        pessoaList = list
        notifyDataSetChanged()
    }
}