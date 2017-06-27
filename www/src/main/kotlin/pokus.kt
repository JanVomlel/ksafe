package cz.aipsafe.ksafe.www

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

fun ahoj(name: String) = "Ahoj $name"

fun main(args: Array<String>) {
    println(ahoj("H"))
}

@WebServlet(name = "Hello", value = "/hello")
class HomeController : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        res.writer.write(ahoj("Worlde"))
    }
}