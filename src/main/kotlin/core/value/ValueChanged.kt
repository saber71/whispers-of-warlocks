package heraclius.core.value

import heraclius.core.dispatcher.DispatcherEvent

class ValueChanged<V>(val oldValue: V, val newVal: V) : DispatcherEvent() {
}
