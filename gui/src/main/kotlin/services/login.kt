package cz.aipsafe.ksafe.gui.services.login

import cz.aipsafe.ksafe.shared.services.login.LoginGetResponse
import cz.aipsafe.ksafe.shared.services.login.LoginPostRequest
import cz.aipsafe.ksafe.shared.services.login.LoginPostResponse
import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise

interface LoginService {

    fun getPromise(): Promise<LoginGetResponse>

    fun postPromise(request: LoginPostRequest): Promise<LoginPostResponse>
    //suspend fun get(): LoginGetResponse
}

class HttpLoginService(val url: String): LoginService {

    override fun getPromise(): Promise<LoginGetResponse> {

        val promise = Promise<LoginGetResponse> {resolve, reject->
            val xhttp = XMLHttpRequest()
            xhttp.open("GET", url, true)
            xhttp.onreadystatechange = {
                val state = xhttp.readyState
                if (state == XMLHttpRequest.DONE) {
                    val status = xhttp.status
                    if (status == 200.toShort()) {
                        val text = xhttp.responseText
                        val response = JSON.parse<LoginGetResponse>(text)
                        resolve(response)
                    } else {
                        reject(Exception("Invalid status"))
                    }
                }
            }
            xhttp.send()
        }
        return promise
    }

    override fun postPromise(request: LoginPostRequest): Promise<LoginPostResponse> {

        val promise = Promise<LoginPostResponse> {resolve, reject->
            val xhttp = XMLHttpRequest()
            xhttp.open("POST", url, true)
            xhttp.onreadystatechange = {
                val state = xhttp.readyState
                if (state == XMLHttpRequest.DONE) {
                    val status = xhttp.status
                    if (status == 200.toShort()) {
                        val text = xhttp.responseText
                        val response = JSON.parse<LoginPostResponse>(text)
                        resolve(response)
                    } else {
                        reject(Exception("Invalid status"))
                    }
                }
            }
            xhttp.overrideMimeType("application/json")
            val requestJSON = JSON.stringify(request)
            xhttp.send(requestJSON)
        }
        return promise
    }
/*
    override suspend fun get(): LoginGetResponse {
        val resp = LoginGetResponse(false, null)
        return resp
    }
*/
}
