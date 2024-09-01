package heraclius.core.resources

import com.google.gson.Gson
import java.io.File

/**
 * Resource 类用于操作文件资源，提供从文件进行 JSON 解析的功能
 *
 * @property file 被操作的文件实例
 */
class Resource(private val file: File) {
    /**
     * 获取文件内容的字符串表示形式
     *
     * @return 文件内容的字符串表示形式
     */
    fun text(): String {
        return file.readText()
    }

    /**
     * 从文件内容中解析出指定类型的对象
     *
     * @param cls 要解析出的对象类型，构造函数入参为 String 类型
     * @return 返回解析出的对象实例
     * @throws InstantiationException 如果指定的类无法实例化，则抛出此异常
     * @throws IllegalAccessException 如果访问权限不允许实例化指定的类，则抛出此异常
     */
    fun <V> fromJSON(cls: Class<V>): V {
        return Gson().fromJson(text(), cls)
    }

    /**
     * 使用JSON对象映射器从JSON字符串反序列化为指定类型对象
     * @param mapper 用于执行JSON字符串到目标类型的映射
     * @return 返回反序列化后的类型实例
     */
    fun <V> fromJSON(mapper: JSONObjectMapper<V>): V {
        return mapper.doMap(text())
    }
}
