package gay.object.hexdebug.mixin;

import at.petrak.hexcasting.api.spell.casting.CastingContext;
import gay.object.hexdebug.core.api.debugging.env.DebugEnvironment;
import gay.object.hexdebug.impl.IDebugEnvAccessor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CastingContext.class)
public abstract class MixinCastingEnvironment implements IDebugEnvAccessor {
    @Unique
    @Nullable
    private DebugEnvironment debugEnv$hexdebug;

    @Override
    @Nullable
    public DebugEnvironment getDebugEnv$hexdebug() {
        return debugEnv$hexdebug;
    }

    @Override
    public void setDebugEnv$hexdebug(@Nullable DebugEnvironment debugEnvironment) {
        debugEnv$hexdebug = debugEnvironment;
    }
}
