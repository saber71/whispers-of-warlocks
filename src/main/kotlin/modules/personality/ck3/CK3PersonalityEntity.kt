package heraclius.modules.personality.ck3

import heraclius.core.ecs.EntityComponent
import heraclius.modules.personality.PersonalityEntity

/**
 * 表示CK3（Crusader Kings III）游戏中角色的个性实体。
 * 继承自通用的个性实体，特定于CK3模块，可能包含与CK3相关的特定组件。
 *
 * @param vararg components 实体初始化时的一组组件，用于定义实体的特性。
 */
class CK3PersonalityEntity(vararg components: EntityComponent<*>) : PersonalityEntity(*components)
