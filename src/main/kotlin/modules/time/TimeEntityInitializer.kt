package heraclius.modules.time

import heraclius.core.ecs.Ecs
import heraclius.core.ecs.EntityInitializer

/**
 * 时间实体初始化器类
 * 该类的目的是在 ECS (Entity-Component-System) 架构中初始化一个代表时间实体的时间实体
 * 它实现了 EntityInitializer 接口，以便在 ECS 引擎初始化过程中执行特定的实体创建任务
 */
class TimeEntityInitializer : EntityInitializer() {
    /**
     * 初始化时间实体
     * 此方法在 ECS 引擎初始化过程中调用，用于创建一个时间实体，并为其添加日期和时间组件
     * 通过这种方式，可以确保每个时间实体都具备处理日期和时间数据的能力
     */
    override fun init() {
        Ecs.createEntity(TimeEntity::class.java, DateComponent(), TimeComponent())
    }
}
