package gay.`object`.hexdebug.casting.actions.splicing

import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingEnvironment
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.iota.Iota
import gay.`object`.hexdebug.blocks.splicing.SplicingTableBlockEntity

object OpReadableClipboard : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val pos = args.getBlockPos(0, argc)
        env.assertPosInRange(pos)

        val table = env.world.getBlockEntity(pos) as? SplicingTableBlockEntity
            ?: return false.asActionResult

        val clipboardHolder = table.clipboardHolder
            ?: return false.asActionResult

        clipboardHolder.readIota(env.world)
            ?: clipboardHolder.emptyIota()
            ?: return false.asActionResult

        return true.asActionResult
    }
}
