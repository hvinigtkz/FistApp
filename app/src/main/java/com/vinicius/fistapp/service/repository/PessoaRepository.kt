package com.vinicius.fistapp.service.repository

import android.content.Context
import com.vinicius.fistapp.service.model.Pessoa
import com.vinicius.fistapp.service.repository.local.FistAppDataBase

class PessoaRepository(context: Context) {
    private val fistAppDb = FistAppDataBase.getDataBase(context).pessoaDAO()


    suspend fun insertPessoa(pessoa: Pessoa){
        fistAppDb.insert(pessoa)
    }
    suspend fun getPessoa(id: Int){
        fistAppDb.getPessoa(id)
    } suspend fun getAll(pessoa: Pessoa){
        fistAppDb.getAll()
    } suspend fun updatePessoa(pessoa: Pessoa){
        fistAppDb.update(pessoa)
    }

    suspend fun deletePessoa(id: Int){
        fistAppDb.delete(id)
    }
}













































































