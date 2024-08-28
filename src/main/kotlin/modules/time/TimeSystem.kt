package heraclius.modules.time

import heraclius.core.ecs.Ecs
import heraclius.core.ecs.EntitySystem
import kotlin.system.exitProcess

// 时间管理系统，用于处理游戏或应用中的时间操作
class TimeSystem : EntitySystem() {
    // 初始化时间系统，创建一个带有时间相关组件的实体
    override fun init() {
        Ecs.createEntity(TimeEntity::class.java, DateComponent(), TimeComponent())
    }

    // 更新时间系统，根据特定组件修改实体的时间值
    override fun update() {
        // 获取第一个时间实体
        val timeEntity = Ecs.first(TimeEntity::class.java)
        // 如果实体有停止组件，则退出应用
        if (timeEntity.hasComponent(StopComponent::class.java)) {
            exitProcess(timeEntity.fetchComponentValue(StopComponent::class.java))
        }
        // 如果实体有暂停组件，则不执行后续时间操作
        if (timeEntity.hasComponent(PauseComponent::class.java)) return
        // 检查并处理日期增加组件，如果有则增加日期
        val dataPlus = timeEntity.getComponentValue(DataPlusComponent::class.java)
        if (dataPlus != null) {
            val dateComponent = timeEntity.fetchComponent(DateComponent::class.java)
            // 根据DataPlusComponent中的值更新日期
            dateComponent.setValue(
                dateComponent.getValue().plusYears(dataPlus.year.toLong()).plusMonths(dataPlus.monthValue.toLong())
                    .plusDays(dataPlus.dayOfMonth.toLong())
            )
        }
        // 检查并处理时间增加组件，如果有则增加时间
        val timePlus = timeEntity.getComponentValue(TimePlusComponent::class.java)
        if (timePlus != null) {
            val timeComponent = timeEntity.fetchComponent(TimeComponent::class.java)
            // 根据TimePlusComponent中的值更新时间
            timeComponent.setValue(
                timeComponent.getValue().plusHours(timePlus.hour.toLong()).plusMinutes(timePlus.minute.toLong())
                    .plusSeconds(timePlus.second.toLong())
            )
        }
        // 检查并处理延迟组件，如果有则线程睡眠指定的延迟时间
        val delay = timeEntity.getComponentValue(DelayComponent::class.java)
        if (delay != null) {
            Thread.sleep(delay)
        }
    }
}
