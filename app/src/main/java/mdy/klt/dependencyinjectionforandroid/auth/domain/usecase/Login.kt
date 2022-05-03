package mdy.klt.dependencyinjectionforandroid.auth.domain.usecase

import kotlinx.coroutines.flow.collectLatest
import mdy.klt.dependencyinjectionforandroid.auth.domain.repository.AuthApiRepository
import mdy.klt.dependencyinjectionforandroid.auth.domain.repository.AuthDsRepository
import mdy.klt.dependencyinjectionforandroid.core.common.Resource
import javax.inject.Inject

class Login @Inject constructor(
    private val api: AuthApiRepository,
    private val ds: AuthDsRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): Resource<String> {
        var result: Resource<String> = Resource.Loading()
        api.login(
            username = username,
            password = password
        ).collectLatest {
            result = when (it) {
                is Resource.Error -> {
                    Resource.Error(errorMessage = it.message ?: "error")
                }
                is Resource.Loading -> {
                    Resource.Loading()
                }
                is Resource.Success -> {
                    ds.putLoggedInState(isLoggedIn = true)
                    Resource.Success(data = "Login success")
                }
            }
        }
        return result
    }
}