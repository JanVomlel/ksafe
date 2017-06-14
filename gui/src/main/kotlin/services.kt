package cz.aipsafe.ksafe.gui.services

import cz.aipsafe.ksafe.gui.services.login.HttpLoginService
import cz.aipsafe.ksafe.gui.services.login.LoginService

interface Services {
    val loginService: LoginService
}

class HttpServices(val url: String): Services {
    override val loginService = HttpLoginService(url+"/login")
}