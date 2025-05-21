package com.desafio.animeapi.data.repository

import android.content.Intent
import com.desafio.animeapi.data.remote.SignInResult
import com.desafio.animeapi.data.remote.User
import com.desafio.animeapi.domain.repository.Authenticator
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class AuthenticatorImpl(
    private val oneTapClient: SignInClient,
    private val auth: FirebaseAuth
) : Authenticator {

    override suspend fun authenticateWithEmailAndPassword(
        email: String,
        password: String
    ): SignInResult {
        val user =
            auth.signInWithEmailAndPassword(email, password).await().user
        return SignInResult(
            data = User(
                username = user?.displayName,
                userId = user?.uid,
                profilePictureUrl = user?.photoUrl.toString()
            ),
            errorMessage = null
        )
    }

    override suspend fun authenticateWithGoogleAuth(intent: Intent): SignInResult {
        val credentials = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credentials.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user =
                auth.signInWithCredential(googleCredentials).await().user
            SignInResult(
                data = user?.run {
                    User(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString()
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    override suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    override suspend fun getSignInUser(): FirebaseUser? = auth.currentUser

}