package gay.`object`.hexdebug.debugger.circles

import at.petrak.hexcasting.api.block.circle.CircleExecutionState

@Suppress("PropertyName")
interface IMixinCircleExecutionState {
    var `debugEnv$hexdebug`: CircleDebugEnv?
}

val CircleExecutionState.mixin get() = this as IMixinCircleExecutionState
