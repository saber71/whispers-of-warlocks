package heraclius.modules.personality

import heraclius.core.ecs.EntityComponent

// 定义PersonalityComponent类，用于表示实体的个性组件
open class PersonalityComponent(value: Float) : EntityComponent<Float>(value) {
}
