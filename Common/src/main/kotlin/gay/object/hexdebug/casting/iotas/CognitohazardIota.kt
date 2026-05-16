package gay.`object`.hexdebug.casting.iotas

import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.iota.IotaType
import at.petrak.hexcasting.api.utils.asTranslatedComponent
import at.petrak.hexcasting.api.utils.black
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.server.level.ServerLevel

/** An iota that terminates a debugging session if attempted to be executed. */
class CognitohazardIota : Iota(TYPE, COGNITOHAZARD_SUBSTITUTE) {
    override fun isTruthy() = true

    override fun toleratesOther(that: Iota) = typesMatch(this, that)

    override fun serialize() = CompoundTag()

    companion object {
        private val COGNITOHAZARD_SUBSTITUTE = Object()

        val DISPLAY = "hexdebug.tooltip.cognitohazard_iota".asTranslatedComponent.black

        val TYPE = object : IotaType<CognitohazardIota>() {
            override fun deserialize(tag: Tag, world: ServerLevel) = CognitohazardIota()

            override fun display(tag: Tag) = DISPLAY

            override fun color() = 0xff_000000.toInt()
        }
    }
}
