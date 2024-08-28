package heraclius.modules.time

import heraclius.core.ecs.MutableEntityComponent
import java.time.LocalTime

class TimeComponent : MutableEntityComponent<LocalTime>(LocalTime.of(0, 0, 0)) {
    fun reset() {
        if (value.hour == 0 && value.minute == 0 && value.second == 0) return
        value = LocalTime.of(0, 0, 0)
    }
}
