package cz.aipsafe.ksafe.www.services.login

import com.google.gson.Gson
import cz.aipsafe.ksafe.shared.services.login.*
import java.nio.charset.Charset
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

val gson = Gson()

@WebServlet(name = "Login", value = loginPath)
class LoginController : HttpServlet() {

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {

        val session = req.getSession(true)
        val user: User? = session.getAttribute("user") as User?

        val response = if (user != null) LoginGetResponse(true, user) else LoginGetResponse(false, null)

        val jsonResponse = gson.toJson(response)

        res.outputStream.write(jsonResponse.toByteArray(Charset.forName("utf-8")))
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {

        val requestStr = req.reader.readText()

        val request = gson.fromJson<LoginPostRequest>(requestStr, LoginPostRequest::class.java)

        val session = req.getSession(true)

        if (request.action == Action.LOGOUT.name) {
            session.setAttribute("user", null)
        } else {
            val login = request.login!!
            if (login.name == login.password) {
                session.setAttribute("user", User(login.name, login.name))
            } else {
                session.setAttribute("user", null)
            }
        }

        val user: User? = session.getAttribute("user") as User?

        val response = if (user != null) LoginPostResponse(true, user) else LoginPostResponse(false, null)

        val jsonResponse = gson.toJson(response)

        res.outputStream.write(jsonResponse.toByteArray(Charset.forName("utf-8")))
    }
}