package mdy.klt.dependencyinjectionforandroid.auth.domain.model

data class ForgotPwdUserVo(
    val newPwd : String,
    val userId : Long,
    val loggedInDevices : Int,
)
