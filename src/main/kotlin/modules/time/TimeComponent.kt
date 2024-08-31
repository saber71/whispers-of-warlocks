package heraclius.modules.time

import heraclius.core.ecs.MutableEntityComponent
import java.time.LocalTime

class TimeComponent : MutableEntityComponent<LocalTime>(LocalTime.of(0, 0, 0)) {
    fun reset() {
        if (`val`.hour == 0 && `val`.minute == 0 && `val`.second == 0) return
        `val` = LocalTime.of(0, 0, 0)
    }
}
