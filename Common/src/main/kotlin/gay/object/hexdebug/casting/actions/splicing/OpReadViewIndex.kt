package gay.`object`.hexdebug.casting.actions.splicing

import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapBadBlock
import gay.`object`.hexdebug.blocks.splicing.SplicingTableBlockEntity

object OpReadViewIndex : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingContext): List<Iota> {
        val pos = args.getBlockPos(0, argc)
        env.assertVecInRange(pos)

        val table = env.world.getBlockEntity(pos) as? SplicingTableBlockEntity
            ?: throw MishapBadBlock.of(pos, "splicing_table")

        return table.viewStartIndex.asActionResult
    }
}
