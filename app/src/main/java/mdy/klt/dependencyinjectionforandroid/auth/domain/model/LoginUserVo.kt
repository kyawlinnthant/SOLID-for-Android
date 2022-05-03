package mdy.klt.dependencyinjectionforandroid.auth.domain.model

data class LoginUserVo(
    val token : String,
    val userId : Long,
    val loggedInDevices : Int,
)
