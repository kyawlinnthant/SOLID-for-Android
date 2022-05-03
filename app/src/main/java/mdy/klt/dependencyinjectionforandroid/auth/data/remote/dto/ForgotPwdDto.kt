package mdy.klt.dependencyinjectionforandroid.auth.data.remote.dto

import kotlinx.serialization.Serializable
import mdy.klt.dependencyinjectionforandroid.auth.domain.model.ForgotPwdUserVo

@Serializable
data class ForgotPwdDto(
    val isSuccess: Boolean,
    val data: NewPwdData?,
    val error : String?
)

@Serializable
data class NewPwdData(
    val newPwd : String,
    val userId : Long,
    val loggedInDevices : Int,
){
    fun toVo(): ForgotPwdUserVo{
        return ForgotPwdUserVo(
            newPwd = newPwd,
            userId = userId,
            loggedInDevices = loggedInDevices
        )
    }
}