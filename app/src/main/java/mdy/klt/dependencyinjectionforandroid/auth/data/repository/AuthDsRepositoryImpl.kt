package mdy.klt.dependencyinjectionforandroid.auth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import mdy.klt.dependencyinjectionforandroid.auth.domain.repository.AuthDsRepository
import mdy.klt.dependencyinjectionforandroid.core.di.Qualifier
import java.io.IOException
import javax.inject.Inject

class AuthDsRepositoryImpl @Inject constructor(
    private val ds: DataStore<Preferences>,
    @Qualifier.Io private val io: CoroutineDispatcher
) : AuthDsRepository {
    companion object {
        val LOGIN_STATE = booleanPreferencesKey(name = "is_logged_in")
    }

    override suspend fun putLoggedInState(isLoggedIn: Boolean) {
        withContext(io) {
            ds.edit {
                it[LOGIN_STATE] = isLoggedIn
            }
        }
    }

    override suspend fun pullLoggedInState(): Flow<Boolean> {
        return ds.data
            .catch { exception -> if (exception is IOException) emit(emptyPreferences()) else throw  exception }
            .map {
                it[LOGIN_STATE] ?: false
            }
    }

    override suspend fun clear() {
        ds.edit {
            it.clear()
        }
    }
}