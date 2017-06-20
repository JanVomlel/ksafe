package cz.aipsafe.ksafe.gui.services.login

import cz.aipsafe.ksafe.gui.services.common.httpGet
import cz.aipsafe.ksafe.gui.services.common.httpPost
import cz.aipsafe.ksafe.shared.services.login.LoginGetResponse
import cz.aipsafe.ksafe.shared.services.login.LoginPostRequest
import cz.aipsafe.ksafe.shared.services.login.LoginPostResponse
import kotlin.js.Promise

interface LoginService {

    fun getPromise(): Promise<LoginGetResponse>

    fun postPromise(request: LoginPostRequest): Promise<LoginPostResponse>
}

class HttpLoginService(val url: String): LoginService {

    override fun getPromise(): Promise<LoginGetResponse> {

        return httpGet(url)

    }

    override fun postPromise(request: LoginPostRequest): Promise<LoginPostResponse> {

        return httpPost(request, url)
    }
}
