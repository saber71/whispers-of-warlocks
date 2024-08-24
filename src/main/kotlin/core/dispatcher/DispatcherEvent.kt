package core.dispatcher

abstract class DispatcherEvent {
    // 打断事件监听器处理流程
    var stop = false
}
