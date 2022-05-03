package mdy.klt.dependencyinjectionforandroid.auth.data.remote.dto

import mdy.klt.dependencyinjectionforandroid.auth.domain.model.SignupUserVo

data class SignupDto(
    val isSuccess: Boolean,
    val data: UserData?,
    val error: String?
)

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