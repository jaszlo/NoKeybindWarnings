package net.jasper.nokeybindwarnings.mixin;

import net.jasper.nokeybindwarnings.NoKeybindWarnings;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeybindsScreen.class)
public class KeybindingScreenMixin {
    @Inject(method="init", at=@At("RETURN"))
    private void injectedConstructor(CallbackInfo ci) {
        ((ScreenInvoker)this).addDrawableChildInvoker(NoKeybindWarnings.toggleWarning);
    }
}
