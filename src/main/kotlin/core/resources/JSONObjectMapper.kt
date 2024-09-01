package heraclius.core.resources

/**
 * JSON对象映射器抽象类
 *
 * 该类作为基础映射器，用于子类实现将JSON字符串转换为特定类型对象的功能
 */
abstract class JSONObjectMapper<V> {
    /**
     * 执行JSON字符串到特定类型对象的映射
     *
     * @param json 需要映射的JSON字符串
     * @return 映射后的特定类型对象
     */
    abstract fun doMap(json: String): V
}
