package cz.aipsafe.ksafe.www.services.search

import com.google.gson.Gson
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

        val request = gson.fromJson<SearchPostRequest>(requestStr, SearchPostRequest::class.java)

        val count = if (request.count >= 0) request.count else 10;

        val items = (1 to count).toList().map { SearchItem(Info(it.toLong(), "aaa ${it}", "aaa"), listOf(Property("id", it))) }

        val response = SearchPostResponse(items)

        val jsonResponse = gson.toJson(response)

        res.outputStream.write(jsonResponse.toByteArray(Charset.forName("utf-8")))
    }
}