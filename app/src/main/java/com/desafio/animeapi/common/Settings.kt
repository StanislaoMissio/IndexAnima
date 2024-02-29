package com.desafio.animeapi.common

import com.desafio.animeapi.data.remote.SignInResult
import com.desafio.animeapi.data.remote.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

inline fun <T1 : Any, T2 : Any, R : Any> let2(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

fun FirebaseUser.toSignInResult(): SignInResult {
    return SignInResult(
        data = User(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl.toString()
        ), errorMessage = ""
    )
}

@Throws
fun <T> DocumentReference.addSnapshotListenerFlow(dataType: Class<T>): Flow<T?> = callbackFlow {
    val listener = EventListener<DocumentSnapshot> { snapshot, exception ->
        if (exception != null) {
            cancel()
            throw Exception(exception.message.toString())
        }
        if (snapshot != null && snapshot.exists()) {
            val data = snapshot.toObject(dataType)
            trySend(data)
        } else {
            throw Exception("There are no snapshot data")
        }

    }

    val registration = addSnapshotListener(listener)
    awaitClose { registration.remove() }

}