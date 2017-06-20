package cz.aipsafe.ksafe.gui.services.common

import org.w3c.xhr.XMLHttpRequest
import kotlin.js.Promise

fun <T, R>httpPost(request: T, url: String): Promise<R> {
    val promise = Promise<R> {resolve, reject->
        val xhttp = XMLHttpRequest()
        xhttp.open("POST", url, true)
        xhttp.onreadystatechange = {
            val state = xhttp.readyState
            if (state == XMLHttpRequest.DONE) {
                val status = xhttp.status
                if (status == 200.toShort()) {
                    val text = xhttp.responseText
                    val response = JSON.parse<R>(text)
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

fun <R>httpGet(url: String): Promise<R> {
    val promise = Promise<R> {resolve, reject->
        val xhttp = XMLHttpRequest()
        xhttp.open("GET", url, true)
        xhttp.onreadystatechange = {
            val state = xhttp.readyState
            if (state == XMLHttpRequest.DONE) {
                val status = xhttp.status
                if (status == 200.toShort()) {
                    val text = xhttp.responseText
                    val response = JSON.parse<R>(text)
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
