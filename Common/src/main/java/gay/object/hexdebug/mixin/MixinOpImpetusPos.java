package gay.object.hexdebug.mixin;

import at.petrak.hexcasting.api.spell.OperatorUtils;
import at.petrak.hexcasting.api.spell.ConstMediaAction;
import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.common.casting.operators.circles.OpImpetusPos;
import gay.object.hexdebug.casting.eval.SplicingTableCastEnv;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(OpImpetusPos.class)
public abstract class MixinOpImpetusPos implements ConstMediaAction {
    @Inject(method = "execute", at = @At("HEAD"), cancellable = true, remap = false)
    private void hexdebug$handleSplicingTableEnv(
        List<? extends Iota> args,
        CastingContext ctx,
        CallbackInfoReturnable<List<? extends Iota>> cir
    ) {
        if (ctx instanceof SplicingTableCastEnv env) {
            cir.setReturnValue(OperatorUtils.getAsActionResult(env.getBlockPos()));
        }
    }
}
