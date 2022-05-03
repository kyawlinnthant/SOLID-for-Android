package mdy.klt.dependencyinjectionforandroid.core.common

object Endpoints {
    const val AUTH_BASE_URL = "http://100.100.100.100/"
    const val CHAT_BASE_URL = "http://100.100.100.200/"
    const val HOME_BASE_URL = "http://100.100.100.300/"
    const val FEED_BASE_URL = "http://100.100.100.400/"

    private const val AUTH_PATH = "auth/"
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val FORGOT_PASSWORD = "forgot-password"

    private const val CHAT_PATH = "chat/"
    const val HISTORIES = "chat-histories"
    const val CONVERSATION_ROOM = "conversations"

    private const val HOME_PATH = "home/"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"

    private const val FEED_PATH = "feed/"
    const val WALL = "wall"
    const val DETAIL = "detail"
    const val UPLOAD = "upload"
}