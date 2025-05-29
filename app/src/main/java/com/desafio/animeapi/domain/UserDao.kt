package com.desafio.animeapi.domain

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.desafio.animeapi.data.remote.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE username IN (:username)")
    suspend fun getUserInfo(username: String): User

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}