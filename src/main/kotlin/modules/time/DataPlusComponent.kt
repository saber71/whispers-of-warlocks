package heraclius.modules.time

import heraclius.core.ecs.EntityComponent
import java.time.LocalDate

class DataPlusComponent(value: LocalDate) : EntityComponent<LocalDate>(value) {
}
