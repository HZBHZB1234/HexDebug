package gay.`object`.hexdebug.registry

import dev.architectury.registry.item.ItemPropertiesRegistry
import gay.`object`.hexdebug.items.DebuggerItem
import gay.`object`.hexdebug.items.EvaluatorItem
import gay.`object`.hexdebug.items.base.ItemPredicateProvider
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.ItemLike

object HexDebugItems : HexDebugRegistrar<Item>(Registry.ITEM_REGISTRY, { Registry.ITEM }) {
    @JvmField
    val DEBUGGER = item("debugger") {
        DebuggerItem(unstackable.rarity(Rarity.UNCOMMON).noTab(), isQuenched = false)
    }

    @JvmField
    val QUENCHED_DEBUGGER = item("quenched_debugger") {
        DebuggerItem(unstackable.rarity(Rarity.RARE).noTab(), isQuenched = true)
    }

    @JvmField
    val EVALUATOR = item("evaluator") {
        EvaluatorItem(unstackable.rarity(Rarity.UNCOMMON), isQuenched = false)
    }

    @JvmField
    val QUENCHED_EVALUATOR = item("quenched_evaluator") {
        EvaluatorItem(unstackable.rarity(Rarity.RARE), isQuenched = true)
    }

    val props: Properties get() = Properties().tab(HexDebugCreativeTabs.HEX_DEBUG)

    private val unstackable get() = props.stacksTo(1)

    override fun init(registerer: (ResourceLocation, Item) -> Unit) {
        super.init(registerer)
        HexDebugActions.init() // TODO: ???????????????????????
    }

    override fun initClient() {
        registerItemProperties()
    }

    private fun registerItemProperties() {
        for (entry in entries) {
            (entry.value as? ItemPredicateProvider)?.getModelPredicates()?.forEach {
                ItemPropertiesRegistry.register(entry.value, it.id, it.predicate)
            }
        }
    }

    private fun <V : Item> item(name: String, builder: () -> V) = ItemEntry(register(name, builder))

    class ItemEntry<V : Item>(entry: Entry<V>) : Entry<V>(entry), ItemLike {
        override fun asItem() = value
    }
}
