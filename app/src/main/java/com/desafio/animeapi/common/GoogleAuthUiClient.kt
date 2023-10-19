package com.desafio.animeapi.common

import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

object GoogleAuthUiClient {

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