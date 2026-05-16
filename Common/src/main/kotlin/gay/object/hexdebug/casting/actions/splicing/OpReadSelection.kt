package gay.`object`.hexdebug.casting.actions.splicing

import at.petrak.hexcasting.api.spell.casting.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingEnvironment
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.iota.DoubleIota
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapBadBlock
import at.petrak.hexcasting.api.spell.orNull
import gay.`object`.hexdebug.blocks.splicing.SplicingTableBlockEntity

object OpReadSelection : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val pos = args.getBlockPos(0, argc)
        env.assertPosInRange(pos)

        val table = env.world.getBlockEntity(pos) as? SplicingTableBlockEntity
            ?: throw MishapBadBlock.of(pos, "splicing_table")

        return listOf(
            table.selection?.start.doubleOrNull(),
            table.selection?.end?.plus(1).doubleOrNull(), // return the exclusive end index
        )
    }
}

private fun Int?.doubleOrNull() = this?.let { DoubleIota(it.toDouble()) }.orNull()
