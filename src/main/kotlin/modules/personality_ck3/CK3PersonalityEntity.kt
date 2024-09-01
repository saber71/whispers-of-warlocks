package heraclius.modules.personality_ck3

import heraclius.core.Symbols
import heraclius.core.ecs.EntityComponent
import heraclius.modules.personality.PersonalityEntity
import heraclius.modules.string.NameComponent

/**
 * 表示CK3（Crusader Kings III）游戏中角色的个性实体。
 * 继承自通用的个性实体，特定于CK3模块，可能包含与CK3相关的特定组件。
 *
 * @param vararg components 实体初始化时的一组组件，用于定义实体的特性。
 */
class CK3PersonalityEntity(vararg components: EntityComponent<*>) : PersonalityEntity(*components) {
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
