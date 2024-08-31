package heraclius.core.dependencies

import kotlin.reflect.KClass

/**
 * `Dependencies` 注释用于在类级别声明该类的依赖项。
 * 它接受一个或多个 `KClass<*>` 类型的参数，这些参数代表了被标注类所依赖的其他类。
 *
 * @param classes 被依赖的类的可变参数列表，类型为 `KClass<*>`。
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Dependencies(vararg val classes: KClass<*>)
