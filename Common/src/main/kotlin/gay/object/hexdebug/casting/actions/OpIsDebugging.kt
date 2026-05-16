package gay.`object`.hexdebug.casting.actions

import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingEnvironment
import at.petrak.hexcasting.api.spell.iota.Iota
import gay.`object`.hexdebug.core.api.HexDebugCoreAPI

object OpIsDebugging : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, env: CastingEnvironment) =
        (HexDebugCoreAPI.INSTANCE.getDebugEnv(env) != null).asActionResult
}
