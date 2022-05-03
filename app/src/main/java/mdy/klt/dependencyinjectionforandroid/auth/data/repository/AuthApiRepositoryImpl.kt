package mdy.klt.dependencyinjectionforandroid.auth.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import mdy.klt.dependencyinjectionforandroid.auth.data.remote.AuthApiService
import mdy.klt.dependencyinjectionforandroid.auth.domain.model.ForgotPwdUserVo
import mdy.klt.dependencyinjectionforandroid.auth.domain.model.LoginUserVo
import mdy.klt.dependencyinjectionforandroid.auth.domain.model.SignupUserVo
import mdy.klt.dependencyinjectionforandroid.auth.domain.repository.AuthApiRepository
import mdy.klt.dependencyinjectionforandroid.core.common.Resource
import mdy.klt.dependencyinjectionforandroid.core.common.safeApiCall
import mdy.klt.dependencyinjectionforandroid.core.di.Qualifier
import javax.inject.Inject

class AuthApiRepositoryImpl @Inject constructor(
    private val api: AuthApiService,
    @Qualifier.Io private val io: CoroutineDispatcher
) : AuthApiRepository {
    override suspend fun login(username: String, password: String): Flow<Resource<LoginUserVo>> {
        return flow {
            emit(Resource.Loading())
            delay(1000L)
            val response = safeApiCall {
                api.login(
                    username = username,
                    password = password,
                )
            }
            when (response) {
                is Resource.Error -> {
                    emit(Resource.Error(errorMessage = response.message ?: "error"))
                }
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
                is Resource.Success -> {
                    emit(Resource.Success(data = response.data?.data!!.toVo()))
                }
            }
        }.flowOn(io)
    }

    override suspend fun signup(
        username: String,
        password: String,
        email: String
    ): Flow<Resource<SignupUserVo>> {
        return flow {
            emit(Resource.Loading())
            delay(1000L)
            val response = safeApiCall {
                api.signup(
                    username = username,
                    password = password,
                    email = email,
                )
            }
            when (response) {
                is Resource.Error -> {
                    emit(Resource.Error(errorMessage = response.message ?: "error"))
                }
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
                is Resource.Success -> {
                    emit(Resource.Success(data = response.data?.data!!.toVo()))
                }
            }
        }.flowOn(io)
    }

    override suspend fun forgotPassword(newPassword: String): Flow<Resource<ForgotPwdUserVo>> {
        return flow {
            emit(Resource.Loading())
            delay(1000L)
            val response = safeApiCall {
                api.forgotPassword(
                    newPassword = newPassword
                )
            }
            when (response) {
                is Resource.Error -> {
                    emit(Resource.Error(errorMessage = response.message ?: "error"))
                }
                is Resource.Loading -> {
                    emit(Resource.Loading())
                }
                is Resource.Success -> {
                    emit(Resource.Success(data = response.data?.data!!.toVo()))
                }
            }
        }.flowOn(io  )
    }
}