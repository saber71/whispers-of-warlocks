package heraclius

import heraclius.core.ReflectionReactor
import heraclius.core.Singleton
import org.reflections.Reflections

fun main() {
    init()
//    while (true) {
//        Ecs.tick()
//    }
}

fun init() {
    val reflections = Reflections("heraclius")
    val classes = reflections.getSubTypesOf(ReflectionReactor::class.java)
    println(classes)
    for (cls in classes) {
        val instance = cls.kotlin.objectInstance ?: Singleton.get(cls);
        instance.doReact(reflections)
    }
}
