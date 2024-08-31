package heraclius.core.datastore

import heraclius.getClass
import heraclius.getParentClasses

/**
 * DataStore 类用于存储和管理应用程序中的数据
 * 它通过 DataStoreKey 为各种类型的数据提供唯一的存储标识符
 * 并支持按键值对存储数据，以及按数据键类型聚合数据
 */
class DataStore {
    // 存储 DataStoreKey 与对应数据对象的映射
    private val dataMap: MutableMap<Any, Any> = HashMap()

    // 存储 DataStoreKey 类型与该类型键关联的值列表的映射
    private val keyClsMapValues: MutableMap<Class<*>, MutableList<Any>> = HashMap()

    private val classMapSuperClasses = HashMap<Class<*>, List<Class<*>>>()

    private fun getParentClassesBy(obj: Any): List<Class<*>> {
        return classMapSuperClasses.computeIfAbsent(getClass(obj)) { _ -> getParentClasses(obj) }
    }

    // 清除所有数据
    fun clear() {
        dataMap.clear()
        keyClsMapValues.clear()
    }

    fun <T : DataStoreKey<*>> put(data: T) {
        this.set(data.id(), data)
    }

    /**
     * 根据给定的键和值设置数据
     * 如果键已存在，则更新其值，并从旧值列表中移除旧值
     *
     * @param key 要设置或更新的 DataStoreKey 对象
     * @param v   与键关联的值
     * @param <V> 值的类型
     * @return 设置的值
    </V> */
    fun <V> set(key: Any, v: V, ignoreSuperClasses: Boolean = false): V {
        val oldValue = dataMap.put(key, v as Any)
        if (key is Class<*>) return v
        if (ignoreSuperClasses) return v
        val superClasses = getParentClassesBy(key)
        for (superClass in superClasses) {
            val list = keyClsMapValues.computeIfAbsent(superClass) { _ -> ArrayList() }
            if (oldValue != null) {
                list.remove(oldValue)
            }
            list.add(v)
        }
        return v
    }

    /**
     * 根据给定的 DataStoreKey 获取存储的数据
     *
     * @param key 用于检索的 DataStoreKey 对象
     * @param <V> 期望返回的值的类型
     * @return 与键关联的值
     */
    fun <V> get(key: DataStoreKey<V>): V? {
        val id = key.id()
        val value = this.dataMap[id]
        @Suppress("UNCHECKED_CAST")
        return value as V?
    }

    /**
     * 使用字符串键获取数据，内部会转换为 DataStoreKey 对象
     *
     * @param key 任意类型的键
     * @param <V> 期望返回的值的类型
     * @return 与键关联的值，如果键不存在则返回 null
     */
    fun <V> get(key: Any): V? {
        @Suppress("UNCHECKED_CAST")
        return this.dataMap[key] as V?
    }

    /**
     * 根据给定的键从数据存储中检索值
     * 此方法用于从数据存储中获取与给定键相关联的值如果键不存在于数据存储中，
     * 则抛出运行时异常
     *
     * @param key 要从数据存储中检索值的键，不能为空
     * @param <V> 期望返回值的类型
     * @return 与给定键相关联的值
     * @throws RuntimeException 如果数据存储中找不到给定的键
    </V> */
    fun <V> fetch(key: DataStoreKey<V>): V {
        val result = this.get(key) ?: throw RuntimeException("DataStoreKey not found")
        return result
    }

    /**
     * 根据键获取数据存储中的值
     *
     * @param key 任意形式的键
     * @param <V> 泛型参数，表示返回的值的类型
     * @return 与键相关联的值
    </V> */
    fun <V> fetch(key: Any): V {
        val result = this.get<V>(key) ?: throw RuntimeException("DataStoreKey not found")
        return result
    }

    /**
     * 获取与指定 DataStoreKey 类型关联的所有值列表
     *
     * @param keyClass DataStoreKey 的类类型
     * @return 与指定键类型关联的值列表，如果类型不存在则返回空列表
    </V> */
    fun <V> getAll(keyClass: Class<*>): List<V> {
        val list = keyClsMapValues[keyClass] ?: return emptyList()
        @Suppress("UNCHECKED_CAST")
        return list.toList() as List<V>
    }

    /**
     * 检查给定的 DataStoreKey 是否存在于数据存储中
     *
     * @param key 要检查的 DataStoreKey 对象
     * @return 如果键存在则返回 true，否则返回 false
     */
    fun <Value> has(key: DataStoreKey<Value>): Boolean {
        return dataMap.containsKey(key)
    }

    /**
     * 使用字符串键检查数据是否存在，内部会转换为 DataStoreKey 对象
     *
     * @param key 任意类型的键
     * @return 如果键存在则返回 true，否则返回 false
     */
    fun has(key: Any): Boolean {
        return dataMap.containsKey(key)
    }

    fun remove(key: Any) {
        if (!dataMap.containsKey(key)) return
        val value = dataMap.remove(key)
        for (superClass in getParentClassesBy(key)) {
            val list = keyClsMapValues[superClass] ?: continue
            list.remove(value)
        }
    }
}
