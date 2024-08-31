package heraclius.core

/**
 * Dict类扩展了HashMap，提供了add和delete方法
 * 用于更方便地添加和删除键值对
 */
class Dict : HashMap<Any, Any>() {

    /**
     * 添加一个键值对到字典中
     *
     * @param key   要添加的键
     * @param value 要添加的值
     * @return 返回Dict实例本身，支持链式调用
     */
    fun add(key: Any, value: Any): Dict {
        this[key] = value
        return this
    }

    /**
     * 从字典中删除指定的键值对
     *
     * @param key 要删除的键
     * @return 返回Dict实例本身，支持链式调用
     */
    fun delete(key: Any): Dict {
        this.remove(key)
        return this
    }
}
