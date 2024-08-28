package heraclius.modules.time

import heraclius.core.ecs.EntityComponent
import java.time.LocalTime

class TimePlusComponent(value: LocalTime) : EntityComponent<LocalTime>(value) {
}
