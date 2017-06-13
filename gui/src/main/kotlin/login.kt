package cz.aipsafe.ksafe.gui.login

import cz.aipsafe.ksafe.gui.app.AppSetup
import cz.aipsafe.ksafe.gui.component.Component
import cz.aipsafe.ksafe.gui.services.login.Action
import cz.aipsafe.ksafe.gui.services.login.Login
import cz.aipsafe.ksafe.gui.services.login.LoginPostRequest
import cz.aipsafe.ksafe.html.generate
import cz.aipsafe.ksafe.html.generator
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLInputElement
import kotlin.browser.document
import kotlin.dom.addClass
import kotlin.dom.clear

/**
 * Login component.
 */
class LoginComponent(val setup: AppSetup): Component {

    /**
     * Event generated when user log in
     */
    var onLogIn = {}

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
        setup.services.loginService.postPromise(request).then({response->
            if (response.logged) {
                onLogIn()
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

