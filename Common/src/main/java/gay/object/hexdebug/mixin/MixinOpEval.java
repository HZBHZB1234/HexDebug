package gay.object.hexdebug.mixin;

import at.petrak.hexcasting.api.casting.castables.Action;
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.eval.OperationResult;
import at.petrak.hexcasting.api.casting.eval.vm.CastingImage;
import at.petrak.hexcasting.api.casting.eval.vm.SpellContinuation;
import at.petrak.hexcasting.api.casting.iota.Iota;
import at.petrak.hexcasting.common.casting.actions.eval.OpEval;
import gay.object.hexdebug.core.api.HexDebugCoreAPI;
import gay.object.hexdebug.core.api.debugging.DebugStepType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


@Mixin(OpEval.class)
public abstract class MixinOpEval implements Action {
    @SuppressWarnings("UnreachableCode")
    @Inject(method = "operate", at = @At("RETURN"), remap = false)
    private void setDebugStepType(
        SpellContinuation continuation,
        List<Iota> stack,
        Iota ravenmind,
        CastingContext ctx,
        CallbackInfoReturnable<OperationResult> cir
    ) {
        var debugEnv = HexDebugCoreAPI.INSTANCE.getDebugEnv(env);
        if (debugEnv != null) {
            debugEnv.setLastEvaluatedAction(this);
            debugEnv.setLastDebugStepType(DebugStepType.IN);
        }
    }
}
