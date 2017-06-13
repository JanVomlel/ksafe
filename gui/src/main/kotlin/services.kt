package cz.aipsafe.ksafe.gui.services.login

interface Services {
    val loginService: LoginService
}

class HttpServices(val url: String): Services {
    override val loginService = HttpLoginService(url+"/login")
}