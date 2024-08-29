package heraclius.modules.personality

import heraclius.core.ecs.EntityComponent

// 定义PersonalityComponent类，用于表示实体的个性组件
// 继承自EntityComponent<Int>，将个性值作为存储的数据
open class PersonalityComponent(value: Int) : EntityComponent<Int>(value) {
    // 构造函数接收一个整型参数value，用于初始化EntityComponent的父类属性
}
