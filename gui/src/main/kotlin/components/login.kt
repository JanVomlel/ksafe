package cz.aipsafe.ksafe.gui.components.login

import cz.aipsafe.ksafe.gui.components.base.Component
import cz.aipsafe.ksafe.gui.components.html.*
import cz.aipsafe.ksafe.gui.components.html.generator
import cz.aipsafe.ksafe.gui.services.login.LoginService
import cz.aipsafe.ksafe.gui.setup.AppSetup
import cz.aipsafe.ksafe.shared.services.login.Action
import cz.aipsafe.ksafe.shared.services.login.Login
import cz.aipsafe.ksafe.shared.services.login.LoginPostRequest
import cz.aipsafe.ksafe.shared.services.login.User
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.clear

/**
 * Login component.
 */
class LoginComponent(val setup: AppSetup, val service: LoginService): Component {

    /**
     * Event generated when user log in
     */
    var onLogIn = {_: User -> }

    private lateinit var errorElement: HTMLDivElement
    private lateinit var userNameElement: HTMLInputElement
    private lateinit var passwordElement: HTMLInputElement

    override val root = document.generator.div {
        el.classList.add(::LoginComponent.name)

        form {
            el.addClass("pure-form")

            fieldSet {
                legend {
                    +"Přihlášení do systému ${setup.title}"
                }
                div(this@LoginComponent::errorElement) {

                }
                input(this@LoginComponent::userNameElement) {
                    el.type="text"
                    el.placeholder="Jméno uživatele"
                }
                input(this@LoginComponent::passwordElement) {
                    el.type="password"
                    el.placeholder="Heslo"
                }
                button {
                    el.addClass("pure-button")
                    el.type="button"
                    el.onclick = {
                        login()
                    }

                    +"Přihlásit"
                }
            }
        }
    }

    private fun login() {

        errorElement.clear()

        val userName = userNameElement.value
        val password = passwordElement.value

        val request = LoginPostRequest(action = Action.LOGIN.name, login = Login(userName, password))
        service.postPromise(request).then({response->
            if (response.logged) {
                onLogIn(response.user!!)
            } else {
                showError("Chybné jméno nebo heslo.")
            }
        }).catch {error->
            showError(error.toString())
        }
    }

    private fun showError(message: String) {
        errorElement.generate {
            div {
                b {
                    +message
                }
            }
        }
    }
}

