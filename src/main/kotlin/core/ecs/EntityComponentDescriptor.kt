package heraclius.core.ecs

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class EntityComponentDescriptor(vararg val names: String)
