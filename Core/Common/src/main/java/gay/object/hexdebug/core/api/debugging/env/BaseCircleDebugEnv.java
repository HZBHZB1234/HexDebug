package gay.object.hexdebug.core.api.debugging.env;

import at.petrak.hexcasting.api.spell.casting.CastingHarness;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ApiStatus.NonExtendable
public abstract class BaseCircleDebugEnv extends DebugEnvironment {
    @Nullable
    private CastingHarness newImage;

    protected BaseCircleDebugEnv(@NotNull ServerPlayer caster) {
        super(caster);
    }

    @Nullable
    public CastingHarness getNewImage() {
        return newImage;
    }

    public void setNewImage(@Nullable CastingHarness newImage) {
        this.newImage = newImage;
    }
}
