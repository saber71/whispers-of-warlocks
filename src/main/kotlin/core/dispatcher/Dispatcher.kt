package core.dispatcher

import java.util.function.Consumer

/**
 * 事件分发器，用于管理和分发[DispatcherEvent]的实现类事件
 */
class Dispatcher {
    // 事件类到处理函数列表的映射，用于存储每个事件类型对应的处理函数
    private val _evtClsMapHandlers: MutableMap<Class<out DispatcherEvent>, ArrayList<Consumer<out DispatcherEvent>>> =
        HashMap()

    /**
     * 清空所有注册的事件处理函数
     */
    fun clear() {
        _evtClsMapHandlers.clear()
    }

    /**
     * 注册一个事件处理函数
     *
     * @param cls     事件的类，表示要处理的事件类型
     * @param handler 事件处理函数，接受一个事件对象并返回null
     * @param <E>     事件的具体类型，必须是[DispatcherEvent]的子类
     * @return 当前{@link Dispatcher}实例，支持链式调用
    </E> */
    fun <E : DispatcherEvent> handle(cls: Class<E>, handler: Consumer<E>): Dispatcher {
        val list = _evtClsMapHandlers.computeIfAbsent(cls) { _ -> ArrayList() }
        list.add(handler)
        return this
    }

    /**
     * 移除一个已经注册的事件处理函数
     *
     * @param cls     事件的类，表示要移除处理函数的事件类型
     * @param handler 要移除的事件处理函数
     * @return 当前{@link Dispatcher}实例，支持链式调用
     */
    fun <E : DispatcherEvent> unhandled(cls: Class<E>, handler: Consumer<E>): Dispatcher {
        val list = _evtClsMapHandlers[cls]
        list?.remove(handler)
        return this
    }

    /**
     * 分发一个事件，触发所有注册了的处理函数
     *
     * @param evt 要分发的事件对象，不能为空
     * @param <E> 事件的具体类型，必须是[DispatcherEvent]的子类
    </E> */
    fun <E : DispatcherEvent> dispatch(evt: E) {
        val list = _evtClsMapHandlers[evt.javaClass]
        if (list != null) {
            for (handler in list) {
                // 确保 handler 的类型与 evt 的类型一致
                @Suppress("UNCHECKED_CAST")
                (handler as Consumer<E>).accept(evt)
                if (evt.stop) {
                    break
                }
            }
        }
    }
}
