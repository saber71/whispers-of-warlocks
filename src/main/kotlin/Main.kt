package heraclius

import heraclius.core.ReflectionReactor
import heraclius.core.Singleton
import org.reflections.Reflections

fun main() {
    val reflections = Reflections("heraclius")
    val classes = reflections.getSubTypesOf(ReflectionReactor::class.java)
    for (cls in classes) {
        val instance = Singleton.get(cls);
        instance.doReact(reflections)
    }
    println(classes)
}
