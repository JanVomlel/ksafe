package cz.aipsafe.ksafe.shared.services.login

class User(val name: String, val fullName: String)

class LoginGetResponse(val logged: Boolean, val user: User?)

class Login(val name: String = "", val password: String = "")

enum class Action {
    LOGIN, LOGOUT
}

class LoginPostRequest(val action: String = Action.LOGOUT.name, val login: Login? = null)

class LoginPostResponse(val logged: Boolean, val user: User?)

const val loginPath = "/login"