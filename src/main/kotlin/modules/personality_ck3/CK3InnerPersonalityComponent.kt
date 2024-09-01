package heraclius.modules.personality_ck3

import heraclius.modules.personality.PersonalityComponent

/**
 * CK3 内部性格组件
 *
 * CK3 内部性格组件是一个抽象类，用于表示 CK3 内部性格组件。
 *
 * @param value 性格值
 */
abstract class CK3InnerPersonalityComponent(value: Number) : PersonalityComponent(value) {
    // 获取 CK3 内部性格的形容词
    abstract fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent>

    // 获取 CK3 内部性格的名词
    abstract fun getNouns(): Class<out CK3PersonalityDescriptionComponent>
}
