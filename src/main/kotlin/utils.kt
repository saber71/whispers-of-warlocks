package heraclius

/**
 * 获取类上的指定注解实例
 *
 * 此函数的目的是从给定的类上检索特定类型的注解实例它通过反射机制遍历类上所有的注解，
 * 并找到与请求注解类型匹配的第一个注解实例如果找不到匹配的注解，则返回null
 *
 * @param clazz 要检索注解的类可以是任何类型的类，但必须带有注解
 * @param annotationClass 要检索的特定注解类型的类例如，要检索`Target`注解，可以传入`Target::class.java`
 * @return 返回与请求注解类型匹配的注解实例，如果找不到则返回null
 */
fun <A : Annotation> getAnnotationValue(clazz: Class<*>, annotationClass: Class<A>): A? {
    @Suppress("UNCHECKED_CAST")
    return clazz.annotations.firstOrNull { it.annotationClass == annotationClass } as? A
}
