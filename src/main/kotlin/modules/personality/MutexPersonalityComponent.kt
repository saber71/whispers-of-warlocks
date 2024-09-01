package heraclius.modules.personality

import heraclius.core.ecs.EntityComponent

// 表示互斥的性格
class MutexPersonalityComponent(englishNames: List<String>) : EntityComponent<List<String>>(englishNames) {
}
