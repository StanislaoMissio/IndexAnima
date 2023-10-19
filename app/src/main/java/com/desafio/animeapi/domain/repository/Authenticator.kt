package com.desafio.animeapi.domain.repository

import android.content.Intent
import com.desafio.animeapi.data.remote.SignInResult
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.FirebaseUser

interface Authenticator {

    suspend fun authenticateWithEmailAndPassword(email: String, password: String): SignInResult
    suspend fun authenticateWithGoogleAuth(intent: Intent): SignInResult
    suspend fun signOut()
    suspend fun getSignInUser(): FirebaseUser?

}