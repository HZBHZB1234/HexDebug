package gay.`object`.hexdebug.registry

import at.petrak.hexcasting.common.lib.hex.HexIotaTypes
import gay.`object`.hexdebug.HexDebug
import gay.`object`.hexdebug.casting.iotas.CognitohazardIota
import net.minecraft.core.Registry

object HexDebugIotaTypes {
    val COGNITOHAZARD = CognitohazardIota.TYPE

    fun init() {
        Registry.register(HexIotaTypes.REGISTRY, HexDebug.id("cognitohazard"), COGNITOHAZARD)
    }
}
