package gay.`object`.hexdebug.casting.actions.splicing

import at.petrak.hexcasting.api.spell.ParticleSpray
import at.petrak.hexcasting.api.spell.RenderedSpell
import at.petrak.hexcasting.api.spell.SpellAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBlockPos
import at.petrak.hexcasting.api.spell.getList
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapBadBlock
import at.petrak.hexcasting.api.spell.mishaps.MishapOthersName
import at.petrak.hexcasting.api.misc.MediaConstants
import gay.`object`.hexdebug.blocks.splicing.SplicingTableBlockEntity
import net.minecraft.world.phys.Vec3

object OpWriteEnlightenedHex : SpellAction {
    override val argc = 2

    override fun execute(args: List<Iota>, env: CastingContext): SpellAction.Result {
        val pos = args.getBlockPos(0, argc)
        val hex = args.getList(1, argc).toList()

        env.assertVecInRange(pos)

        val table = env.world.getBlockEntity(pos) as? SplicingTableBlockEntity
            ?: throw MishapBadBlock.of(pos, "splicing_table")

        if (!table.enlightened) {
            throw MishapBadBlock.of(pos, "splicing_table.enlightened")
        }

        MishapOthersName.getTrueNameFromArgs(hex, null)?.let {
            throw MishapOthersName(it)
        }

        return SpellAction.Result(
            Spell(table, hex),
            5 * MediaConstants.CRYSTAL_UNIT,
            listOf(ParticleSpray(pos.center, Vec3(1.0, 0.0, 0.0), 0.25, 3.14, 40))
        )
    }

    private data class Spell(val table: SplicingTableBlockEntity, val hex: List<Iota>) : RenderedSpell {
        override fun cast(env: CastingContext) {
            table.setHex(hex)
            table.sync()
        }
    }
}
