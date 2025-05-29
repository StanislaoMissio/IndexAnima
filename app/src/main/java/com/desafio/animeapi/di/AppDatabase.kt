package com.desafio.animeapi.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.desafio.animeapi.data.remote.User
import com.desafio.animeapi.domain.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun UserDao(): UserDao
}