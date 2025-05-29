package com.desafio.animeapi.common

import android.content.IntentSender
import androidx.credentials.GetCredentialRequest
import com.desafio.animeapi.BuildConfig
import com.desafio.animeapi.common.Constants.SHA_STRING
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID
import java.util.concurrent.CancellationException

object GoogleAuthUiClient {

    private fun configureCredentialManager(): GetCredentialRequest {
        val digest =
            MessageDigest.getInstance(SHA_STRING).digest(UUID.randomUUID().toString().toByteArray())
        val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) }

        val googleIdOptions = GetGoogleIdOption.Builder()
            .setServerClientId(BuildConfig.OAUTH_KEY)
            .setNonce(hashedNonce)
            .setFilterByAuthorizedAccounts(true)
            .setAutoSelectEnabled(true)
            .build()

        return GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOptions)
            .build()
    }

    suspend fun signIn(oneTapClient: SignInClient): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                beginSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    private fun beginSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(Constants.DEFAULT_WEB_ID)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}