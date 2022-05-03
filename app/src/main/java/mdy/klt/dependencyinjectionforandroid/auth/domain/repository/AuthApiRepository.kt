package mdy.klt.dependencyinjectionforandroid.auth.domain.repository

import kotlinx.coroutines.flow.Flow
import mdy.klt.dependencyinjectionforandroid.auth.domain.model.ForgotPwdUserVo
import mdy.klt.dependencyinjectionforandroid.auth.domain.model.LoginUserVo
import mdy.klt.dependencyinjectionforandroid.auth.domain.model.SignupUserVo
import mdy.klt.dependencyinjectionforandroid.core.common.Resource

interface AuthApiRepository {

    suspend fun login(
        username: String,
        password: String
    ): Flow<Resource<LoginUserVo>>

    suspend fun signup(
        username: String,
        password: String,
        email: String
    ): Flow<Resource<SignupUserVo>>

    suspend fun forgotPassword(newPassword: String): Flow<Resource<ForgotPwdUserVo>>

}