package cz.aipsafe.ksafe.shared.services.search

const val searchPath = "/search"

class SearchPostRequest(
        val query: String,
        val from: Int = 0,
        val count: Int = -1,
        val metadata: Boolean = true,
        val fullCount: Boolean = false
)

class Metadata()

class Info(
        val id: Long,
        val display: String,
        val defName: String
)

class Property(
        val name: String,
        val value: Any
)

class SearchItem(
        val info: Info,
        val properties: List<Property>
)

class SearchPostResponse(
        val items: List<SearchItem>,
        val metadata: Metadata? = null,
        val fullCount: Int? = null
)

