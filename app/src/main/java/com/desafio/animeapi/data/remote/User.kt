package com.desafio.animeapi.data.remote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.desafio.animeapi.domain.model.Anime

@Entity
data class User(
    @PrimaryKey
    val userId: Int = 0,
    @ColumnInfo("username") val username: String? = "",
    @ColumnInfo("profile_picture") val profilePictureUrl: String? = "",
    @ColumnInfo("favorite_list") val favoriteList: List<Anime> = emptyList(),
    @ColumnInfo("lists") val lists: List<List<Anime>> = emptyList()
)