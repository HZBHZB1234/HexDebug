package gay.`object`.hexdebug.splicing

import at.petrak.hexcasting.api.spell.casting.ResolvedPatternType
import at.petrak.hexcasting.api.spell.math.HexPattern
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.Container
import net.minecraft.world.item.ItemStack

const val IOTA_BUTTONS = 9
const val VIEW_END_INDEX_OFFSET = IOTA_BUTTONS - 1

interface ISplicingTable : Container {
    fun getClientView(): SplicingTableClientView?

    fun listStackChanged(stack: ItemStack) {}

    fun clipboardStackChanged(stack: ItemStack) {}

    fun runAction(action: SplicingTableAction, player: ServerPlayer?)

    fun drawPattern(player: ServerPlayer?, pattern: HexPattern, index: Int): ResolvedPatternType

    fun selectIndex(player: ServerPlayer?, index: Int, hasShiftDown: Boolean, isIota: Boolean)

    fun castHex(player: ServerPlayer?)
}
