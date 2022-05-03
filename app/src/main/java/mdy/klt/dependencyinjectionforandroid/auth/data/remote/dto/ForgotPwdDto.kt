package mdy.klt.dependencyinjectionforandroid.auth.data.remote.dto

import mdy.klt.dependencyinjectionforandroid.auth.domain.model.ForgotPwdUserVo

data class ForgotPwdDto(
    val isSuccess: Boolean,
    val data: NewPwdData?,
    val error : String?
)

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