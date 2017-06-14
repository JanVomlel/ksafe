package cz.aipsafe.ksafe.gui.model.login

import cz.aipsafe.ksafe.gui.services.login.*
import kotlin.js.Promise

class LoginModel(val loginService: LoginService) {

    private var _user: User? = null
    private var _error: String = ""

    val user: User? get() = _user
    val logged: Boolean get() = user != null
    val error get() = _error

    val onChange = mutableSetOf< ()->Unit>()

    private fun notifyOnChange() {
        onChange.forEach { it.invoke() }
    }

    fun login(name: String, password: String): Promise<Unit> {

        val request = LoginPostRequest(Action.LOGIN.toString(), Login(name, password))
        val result = loginService.postPromise(request).then({response->
            _error = ""
            _user = response.user
            notifyOnChange()
        }).catch {error->
            _error = error.toString()
            loadCurrentState()
        }

        return result.unsafeCast<Promise<Unit>>();

    }

    fun logout(): Promise<Unit> {
        val request = LoginPostRequest(Action.LOGOUT.toString())
        val result = loginService.postPromise(request).then({response->
            _user = null
            notifyOnChange()
        }).catch {error->
            _error = error.toString()
            loadCurrentState()
        }

        return result.unsafeCast<Promise<Unit>>();
    }

    private fun loadCurrentState(): Promise<Unit> {
        return loginService.getPromise().then({
            response->
            _user = response.user
            notifyOnChange()
        }, {
            error->
            _error = error.toString()
            notifyOnChange()
        })
    }

    init {
        loadCurrentState()
        val a = loginService.getPromise().then({
            loginService.getPromise()
        })
    }

}
