package gay.`object`.hexdebug.debugger.circles

import at.petrak.hexcasting.api.spell.circles.BlockEntityAbstractImpetus
import at.petrak.hexcasting.api.spell.circles.CircleExecutionState
import at.petrak.hexcasting.api.spell.casting.CastingEnvironment
import at.petrak.hexcasting.api.spell.casting.ResolvedPatternType
import at.petrak.hexcasting.api.spell.casting.env.PlayerBasedCastEnv.AMBIT_RADIUS
import at.petrak.hexcasting.api.spell.casting.CastingImage
import gay.`object`.hexdebug.core.api.debugging.env.BaseCircleDebugEnv
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer

class CircleDebugEnv(caster: ServerPlayer, val pos: BlockPos) : BaseCircleDebugEnv(caster) {
    var isPaused = false

    private val blockEntity get() = caster.serverLevel().getBlockEntity(pos) as? BlockEntityAbstractImpetus

    private fun getExecutionState(maybeBlockEntity: BlockEntityAbstractImpetus? = null): CircleExecutionState? {
        return (maybeBlockEntity ?: this.blockEntity)
            ?.executionState
            ?.takeIf { it.mixin.`debugEnv$hexdebug`?.sessionId == sessionId }
    }

    override fun resume(
        env: CastingEnvironment,
        image: CastingImage,
        resolutionType: ResolvedPatternType,
    ): Boolean {
        if (!resolutionType.success) return false
        isPaused = false
        newImage = image
        return getExecutionState() != null
    }

    override fun restart(threadId: Int) {
        terminate()
        blockEntity?.mixin?.startDebugging(caster, threadId)
    }

    override fun terminate() {
        blockEntity?.let {
            it.endExecution()
            it.mixin.`hexdebug$clearExecutionState`()
        }
    }

    override fun isCasterInRange(): Boolean {
        return caster.distanceToSqr(pos.center) <= AMBIT_RADIUS * AMBIT_RADIUS
    }

    override fun getName(): Component {
        return caster.serverLevel().getBlockState(pos).block.name
    }
}
