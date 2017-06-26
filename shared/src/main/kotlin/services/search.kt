package cz.aipsafe.ksafe.shared.services.search

import cz.aipsafe.ksafe.shared.services.common.*

const val searchPath = "/search"

class SearchPostRequest(
        val query: String,
        val from: Int = 0,
        val count: Int = -1,
        val metadata: Boolean = true,
        val fullCount: Boolean = false
) {
    fun jsonize() = JSONSearchPostRequest(query, from, count, metadata, fullCount)
}

class SearchPostResponse(
        val items: List<SearchItem>,
        val metadata: Metadata? = null,
        val fullCount: Int? = null
) {
    fun jsonize() = JSONSearchPostResponse(items.map{it.jsonize()}.toTypedArray(), metadata, fullCount)
}

class SearchItem(val info: ObjectInfo, val properties: List<Property>) {
    fun jsonize() = JSONSearchItem(info.jsonize(), properties.map { it.jsonize() }.toTypedArray())
}

class Metadata()

class JSONSearchPostRequest(
        val query: String,
        val from: Int = 0,
        val count: Int = -1,
        val metadata: Boolean = true,
        val fullCount: Boolean = false
)
fun JSONSearchPostRequest.normalize() = SearchPostRequest(query, from, count, metadata, fullCount)

class JSONSearchPostResponse(
        val items: Array<JSONSearchItem>,
        val metadata: Metadata? = null,
        val fullCount: Int? = null
)
fun JSONSearchPostResponse.normalize() = SearchPostResponse(items.map{it.normalize()}, metadata, fullCount)

class JSONSearchItem(val info: JSONObjectInfo, val properties: Array<JSONProperty>)
fun JSONSearchItem.normalize() = SearchItem(info.normalize(), properties.map { it.normalize() })

