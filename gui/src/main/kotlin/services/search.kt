package cz.aipsafe.ksafe.gui.services.login

import cz.aipsafe.ksafe.gui.services.common.httpPost
import cz.aipsafe.ksafe.shared.services.search.SearchPostRequest
import cz.aipsafe.ksafe.shared.services.search.SearchPostResponse
import kotlin.js.Promise

interface SearchService {

    fun postPromise(request: SearchPostRequest): Promise<SearchPostResponse>
}

class HttpSearchService(val url: String): SearchService {

    override fun postPromise(request: SearchPostRequest): Promise<SearchPostResponse> {

        return httpPost(request, url)
    }
}
