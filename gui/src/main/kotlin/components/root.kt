package cz.aipsafe.ksafe.gui.components.root

import cz.aipsafe.ksafe.gui.components.app.AppComponent
import cz.aipsafe.ksafe.gui.components.base.Component
import cz.aipsafe.ksafe.gui.components.html.*
import cz.aipsafe.ksafe.gui.components.login.LoginComponent
import cz.aipsafe.ksafe.gui.model.AppModel
import cz.aipsafe.ksafe.gui.services.Services
import cz.aipsafe.ksafe.gui.setup.AppSetup
import cz.aipsafe.ksafe.shared.services.login.Action
import cz.aipsafe.ksafe.shared.services.login.LoginPostRequest
import cz.aipsafe.ksafe.shared.services.login.User
import kotlin.browser.document
import kotlin.dom.appendText

/**
 * Application root component. Contains login dialog and runs application.
 */
class RootComponent(val setup: AppSetup, val services: Services): Component {

    override val root = document.generator.div {
        el.classList.add(::RootComponent.name)
    }

    init {
        services.loginService.getPromise().then( {response->
            if (response.logged) {
                showApp(response.user!!)
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
        val loginComponent = LoginComponent(setup, services.loginService).apply {
            onLogIn = {user->showApp(user)}
        }
        root.regenerate {
            +loginComponent.root
        }
    }

    private fun showApp(user: User) {
        val appComponent = AppComponent(setup, AppModel(services, user)).apply {
            onLogoutRequest = {
                val request = LoginPostRequest(Action.LOGOUT.name, null)
                services.loginService.postPromise(request).then({
                    showLogin()
                })
            }
        }
        root.regenerate {
            +appComponent.root
        }
    }
}

