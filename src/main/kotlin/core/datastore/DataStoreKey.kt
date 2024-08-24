package heraclius.core.datastore

open class DataStoreKey<Value>(private val _id: Any) {
    fun id(): Any {
        return _id
    }
}
