package mdy.klt.dependencyinjectionforandroid.auth.data.remote.dto

import kotlinx.serialization.Serializable
import mdy.klt.dependencyinjectionforandroid.auth.domain.model.LoginUserVo

@Serializable
data class LoginDto(
    val isSuccess: Boolean,
    val data: LoginUserData?,
    val error: String?
)

@Serializable
data class LoginUserData(
    val token: String,
    val userId: Long,
    val loggedInDevices: Int,
) {
    fun toVo(): LoginUserVo {
        return LoginUserVo(
            token = token,
            userId = userId,
            loggedInDevices = loggedInDevices
        )
    }
}