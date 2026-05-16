package gay.object.hexdebug.core.api.debugging;

import at.petrak.hexcasting.api.spell.casting.SpellCircleContext;
import at.petrak.hexcasting.api.spell.casting.CastingHarness;
import gay.object.hexdebug.core.api.debugging.env.BaseCircleDebugEnv;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.state.BlockState;

public interface DebuggableCircleComponent {
    void acceptDebugControlFlow(
        ServerPlayer caster,
        BaseCircleDebugEnv debugEnv,
        CastingHarness imageIn,
        SpellCircleContext env,
        Direction enterDir,
        BlockPos pos,
        BlockState bs
    );
}
