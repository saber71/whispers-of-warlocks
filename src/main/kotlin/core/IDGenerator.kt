package heraclius.core

/**
 * ID生成器，提供全局唯一的递增ID
 * 采用单例模式，确保在应用程序中只有一个ID生成器实例
 */
object IDGenerator {
    // 当前ID值，初始为0，保证每次调用get()方法时，都能返回一个新的ID
    private var value = 0L

    /**
     * 获取一个新的唯一ID
     * 通过自增的方式保证每次返回的ID都是唯一的
     *
     * @return 一个新的唯一ID
     */
    fun get(): Long {
        return ++value
    }
}
