package mdy.klt.dependencyinjectionforandroid.auth.data.remote.dto

import kotlinx.serialization.Serializable
import mdy.klt.dependencyinjectionforandroid.auth.domain.model.SignupUserVo

@Serializable
data class SignupDto(
    val isSuccess: Boolean,
    val data: UserData?,
    val error: String?
)

@Serializable
data class UserData(
    val token: String,
    val userId: Long,
) {
    fun toVo(): SignupUserVo {
        return SignupUserVo(
            token = token,
            userId = userId,
        )
    }
}