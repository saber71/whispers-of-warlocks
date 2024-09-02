package heraclius.core.resources

import java.io.File
import java.nio.file.Path

/**
 * 资源加载器对象，提供了一种从类路径中加载资源文件的机制
 */
object ResourceLoader {
    // 缓存已加载的资源，避免重复加载同一个资源
    private val resourceCache = mutableMapOf<String, Resource>()

    /**
     * 根据给定的路径加载资源
     *
     * 此函数提供了一种根据路径字符串加载资源的方式它将路径转换为字符串，
     * 然后调用另一个重载的 `load` 函数来完成实际的资源加载操作
     *
     * @param path 资源的路径，类型为 `Path`，表示资源在文件系统中的位置
     * @return 返回一个 `Resource` 对象，表示加载的资源
     */
    fun load(path: Path): Resource {
        return load(path.toString())
    }

    /**
     * 加载指定路径的资源文件
     *
     * @param path 资源文件在类路径中的相对路径
     * @return 资源文件的包装对象，如果资源不存在则返回null
     */
    fun load(path: String): Resource {
        // 如果资源已缓存，则直接返回缓存的资源
        if (resourceCache.containsKey(path)) return resourceCache[path]!!

        // 尝试通过类加载器获取资源的URL，如果资源不存在则返回null
        val url = ResourceLoader::class.java.classLoader.getResource(path)
            ?: throw RuntimeException("Resource not found: $path")

        // 将URL转换为文件路径，并创建Resource对象
        val resource = Resource(File(url.toURI()))

        // 将资源缓存起来，以便下次快速加载
        resourceCache[path] = resource

        // 返回新创建的Resource对象
        return resource
    }
}
