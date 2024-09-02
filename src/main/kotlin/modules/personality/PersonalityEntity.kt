package heraclius.modules.personality

import heraclius.core.Symbols
import heraclius.core.ecs.Entity
import heraclius.core.ecs.EntityComponent
import heraclius.modules.string.NameComponent

open class PersonalityEntity(vararg components: EntityComponent<*>) : Entity(*components) {
    init {
        // 遍历所有组件，寻找NameComponent以设置为id
        for (component in components) {
            if (component is NameComponent) {
                // 使用NameComponent的值设置id值
                Symbols.of(component.value(), symbol())
                break
            }
        }
    }
}
