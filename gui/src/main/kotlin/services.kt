package cz.aipsafe.ksafe.gui.services

import cz.aipsafe.ksafe.gui.services.login.HttpLoginService
import cz.aipsafe.ksafe.gui.services.login.HttpSearchService
import cz.aipsafe.ksafe.gui.services.login.LoginService
import cz.aipsafe.ksafe.gui.services.login.SearchService
import cz.aipsafe.ksafe.shared.services.login.loginPath
import cz.aipsafe.ksafe.shared.services.search.searchPath

interface Services {
    val loginService: LoginService
    val searchService: SearchService
}

class HttpServices(val url: String): Services {
    override val loginService = HttpLoginService(url+loginPath)
    override val searchService = HttpSearchService(url+searchPath)
}