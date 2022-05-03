package mdy.klt.dependencyinjectionforandroid.auth.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import mdy.klt.dependencyinjectionforandroid.auth.data.remote.AuthApiService
import mdy.klt.dependencyinjectionforandroid.auth.data.repository.AuthApiRepositoryImpl
import mdy.klt.dependencyinjectionforandroid.auth.data.repository.AuthDsRepositoryImpl
import mdy.klt.dependencyinjectionforandroid.auth.domain.repository.AuthApiRepository
import mdy.klt.dependencyinjectionforandroid.auth.domain.repository.AuthDsRepository
import mdy.klt.dependencyinjectionforandroid.core.common.Constants
import mdy.klt.dependencyinjectionforandroid.core.common.Endpoints
import mdy.klt.dependencyinjectionforandroid.core.di.Qualifier
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor = OkHttpProfilerInterceptor())
            .connectTimeout(timeout = 60, unit = TimeUnit.SECONDS)
            .readTimeout(timeout = 60, unit = TimeUnit.SECONDS)
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(Endpoints.AUTH_BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthApiRepository(
        api: AuthApiService,
        @Qualifier.Io io: CoroutineDispatcher
    ): AuthApiRepository {
        return AuthApiRepositoryImpl(
            api = api,
            io = io
        )
    }

    @Provides
    @Singleton
    fun providesAuthPreferencesDs(
        @ApplicationContext appContext: Context,
        @Qualifier.Io io: CoroutineDispatcher,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { appContext.preferencesDataStoreFile(name = Constants.AUTH_DS_NAME) },
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(
                SharedPreferencesMigration(
                    context = appContext,
                    sharedPreferencesName = Constants.AUTH_DS_NAME
                )
            ),
            scope = CoroutineScope(io + SupervisorJob()),
        )
    }

    @Provides
    @Singleton
    fun providesAuthDsRepository(
        ds: DataStore<Preferences>,
        @Qualifier.Io io: CoroutineDispatcher
    ): AuthDsRepository {
        return AuthDsRepositoryImpl(
            ds = ds,
            io = io
        )
    }
}