package heraclius.core.injectify

/**
 * Injectable注解用于标识一个类是可以被自动注入依赖的
 * 该注解被用于类的定义上，以便在运行时指导依赖注入框架自动处理该类的依赖注入
 * 被标注的类将会是单例的
 */
@Retention(AnnotationRetention.RUNTIME) // 指定注解的保留策略为运行时，使得在运行时可以读取到此注解
@Target(AnnotationTarget.CLASS) // 指定注解的目标为类型（类、接口等）
annotation class Injectable(val singleton: Boolean = true)
