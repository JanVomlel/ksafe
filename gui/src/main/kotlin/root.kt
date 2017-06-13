package cz.aipsafe.ksafe.gui.root

import cz.aipsafe.ksafe.gui.app.AppComponent
import cz.aipsafe.ksafe.gui.app.AppSetup
import cz.aipsafe.ksafe.gui.component.Component
import cz.aipsafe.ksafe.gui.login.LoginComponent
import cz.aipsafe.ksafe.gui.services.login.Action
import cz.aipsafe.ksafe.gui.services.login.LoginPostRequest
import cz.aipsafe.ksafe.gui.services.login.Services
import cz.aipsafe.ksafe.html.*
import kotlin.browser.document
import kotlin.dom.appendText

/**
 * Application root component. Contains login dialog and runs application.
 */
class RootComponent(val setup: AppSetup): Component {

    override val root = document.generator.div {
        el.classList.add(::RootComponent.name)
    }

    init {
        setup.services.loginService.getPromise().then( {response->
            if (response.logged) {
                showApp()
            } else {
                showLogin()
            }
        }).catch {error->
            showError(error)
        }
    }

    private fun  showError(error: Throwable) {
        root.appendText(error.toString())
    }

    private fun showLogin() {
        val loginComponent = LoginComponent(setup).apply {
            onLogIn = {showApp()}
        }
        root.regenerate {
            +loginComponent.root
        }
    }

    private fun showApp() {
        val appComponent = AppComponent(setup).apply {
            onLogoutRequest = {
                val request = LoginPostRequest(Action.LOGOUT.name, null)
                setup.services.loginService.postPromise(request).then({
                    showLogin()
                })
            }
        }
        root.regenerate {
            +appComponent.root
        }
    }
}

