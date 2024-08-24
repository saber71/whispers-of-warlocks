package heraclius

import org.reflections.Reflections

fun main() {
    val reflections = Reflections("heraclius")
    val classes = reflections.getSubTypesOf(Enum::class.java)
    println(classes)
}
