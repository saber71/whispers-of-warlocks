package heraclius.modules.personality

import heraclius.core.ecs.EntityComponent

// 表示互斥的性格
class MutexPersonalityComponent(englishName: String) : EntityComponent<String>(englishName) {
}
