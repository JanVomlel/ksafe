package cz.aipsafe.ksafe.www.services.search

import com.google.gson.Gson
import cz.aipsafe.ksafe.shared.services.common.ObjectInfo
import cz.aipsafe.ksafe.shared.services.common.Property
import cz.aipsafe.ksafe.shared.services.common.StringPropertyValue
import cz.aipsafe.ksafe.shared.services.search.*
import java.nio.charset.Charset
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

val gson = Gson()

@WebServlet(name = "Search", value = searchPath)
class SearchController : HttpServlet() {

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {

        val requestStr = req.reader.readText()
        val requestJSON = gson.fromJson<JSONSearchPostRequest>(requestStr, JSONSearchPostRequest::class.java)
        val request = requestJSON.normalize()

        val count = if (request.count >= 0) request.count else 10

        val items = (1 to count).toList().map { SearchItem(ObjectInfo(it.toLong(), "aaa $it", "aaa"), listOf(Property("id", StringPropertyValue(it.toString())))) }

        val response = SearchPostResponse(items)
        val responseJSON = response.jsonize()
        val responseStr = gson.toJson(responseJSON)

        res.outputStream.write(responseStr.toByteArray(Charset.forName("utf-8")))
    }
}