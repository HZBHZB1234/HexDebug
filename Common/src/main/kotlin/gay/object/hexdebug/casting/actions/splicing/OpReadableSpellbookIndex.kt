package gay.`object`.hexdebug.casting.actions.splicing

import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.Mishap

class OpReadableSpellbookIndex(private val useListItem: Boolean) : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingContext): List<Iota> {
        val pos = args.getBlockPos(0, argc)
        env.assertVecInRange(pos)

        return try {
            OpReadSpellbookIndex.getSpellbook(env, pos, useListItem)
            true
        } catch (e: Mishap) {
            false
        }.asActionResult
    }
}
