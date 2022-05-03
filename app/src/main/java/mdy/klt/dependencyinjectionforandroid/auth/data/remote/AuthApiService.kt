package mdy.klt.dependencyinjectionforandroid.auth.data.remote

import mdy.klt.dependencyinjectionforandroid.auth.data.remote.dto.ForgotPwdDto
import mdy.klt.dependencyinjectionforandroid.auth.data.remote.dto.LoginDto
import mdy.klt.dependencyinjectionforandroid.auth.data.remote.dto.SignupDto
import mdy.klt.dependencyinjectionforandroid.core.common.Endpoints
import retrofit2.Response
import retrofit2.http.POST

interface AuthApiService {
    @POST(Endpoints.LOGIN)
    suspend fun login(
        username: String,
        password: String
    ): Response<LoginDto>

    @POST(Endpoints.SIGNUP)
    suspend fun signup(
        username: String,
        password: String,
        email: String
    ): Response<SignupDto>

    @POST(Endpoints.FORGOT_PASSWORD)
    suspend fun forgotPassword(newPassword: String): Response<ForgotPwdDto>

}