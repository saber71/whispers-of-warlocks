package heraclius.modules.time

import heraclius.core.ecs.MutableEntityComponent
import java.time.LocalDate

class DateComponent : MutableEntityComponent<LocalDate>(LocalDate.of(0, 0, 0)) {
}
