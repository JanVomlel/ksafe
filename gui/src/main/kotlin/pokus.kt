class A(val b: String, val c: Array<String>)

fun main(args: Array<String>) {
    val a = JSON.parse<A>("""{"b": "b_value", "c": ["c_value_1", "c_value_2"]}""")
    //val a = A("b_value", listOf("c_value_1", "c_value_2"))
    println(a.b)
    for (c in a.c) println(c)
}

