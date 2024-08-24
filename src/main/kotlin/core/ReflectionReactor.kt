package heraclius.core

import org.reflections.Reflections

/**
 * 反射处理器的抽象类。
 *
 * 此类作为处理反射相关任务的基础类，定义了一个必须由子类实现的方法doReact。
 * 该方法的目的是对通过Reflections库扫描得到的类信息进行处理和响应。
 *
 * 子类构造函数必须是无参的
 */
abstract class ReflectionReactor {
    /**
     * 执行具体的反射处理操作。
     *
     * @param reflections Reflections实例，用于访问扫描到的类信息。
     * 该实例包含了通过反射扫描得到的所有类、方法、注解等信息，是反射处理操作的数据来源。
     */
    abstract fun doReact(reflections: Reflections)
}
