package com.vinicius.fistapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vinicius.fistapp.service.model.Pessoa
import com.vinicius.fistapp.service.repository.PessoaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllPessoasViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =  PessoaRepository(application)

    private val mPessoaList = MutableLiveData<List<Pessoa>>()
    val pessoaList: LiveData<List<Pessoa>> = mPessoaList

    fun loadPessoas(){
        viewModelScope.launch (Dispatchers.IO) {
            mPessoaList.postValue(repository.getAll())
        }
    }
}