package me.logwet.walker.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.player.KeyboardInput;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(KeyboardInput.class)
public abstract class KeyboardInputMixin {
    @Redirect(
            method = "tick",
            at =
                    @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/client/player/KeyboardInput;up:Z",
                            opcode = Opcodes.PUTFIELD))
    private void overrideForwardInput(KeyboardInput keyboardInput, boolean value) {
        keyboardInput.up = false;
    }

    @Redirect(
            method = "tick",
            at =
                    @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/client/player/KeyboardInput;down:Z",
                            opcode = Opcodes.PUTFIELD))
    private void overrideBackwardsInput(KeyboardInput keyboardInput, boolean value) {
        keyboardInput.down = true;
    }
}
