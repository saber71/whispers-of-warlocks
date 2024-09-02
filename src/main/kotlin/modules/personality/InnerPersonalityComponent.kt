package heraclius.modules.personality

import heraclius.core.ecs.EntityComponent
import heraclius.core.value.Value

// 定义InnerPersonalityComponent类，用于表示实体的隐藏个性组件
open class InnerPersonalityComponent(value: Value<Number>) : EntityComponent<Value<Number>>(value) {
}
