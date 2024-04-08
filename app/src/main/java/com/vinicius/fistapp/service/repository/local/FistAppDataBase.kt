package com.vinicius.fistapp.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vinicius.fistapp.service.model.Pessoa

@Database(entities = [Pessoa::class], version = 1)
abstract class FistAppDataBase : RoomDatabase() {


    abstract  fun pessoaDAO(): PessoaDAO

    companion object {
        @Volatile
        private lateinit var INSTANCE: FistAppDataBase

        fun getDataBase(context: Context): FistAppDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        FistAppDataBase::class.java,
                        "fistapp_database"
                    ).build()
                }
            }
            return  INSTANCE
        }
    }
}