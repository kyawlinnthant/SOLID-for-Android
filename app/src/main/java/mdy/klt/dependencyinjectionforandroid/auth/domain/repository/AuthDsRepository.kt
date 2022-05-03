package mdy.klt.dependencyinjectionforandroid.auth.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthDsRepository {
    suspend fun putLoggedInState(isLoggedIn: Boolean)
    suspend fun pullLoggedInState(): Flow<Boolean>
    suspend fun clear()
}