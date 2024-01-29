package net.jasper.nokeybindwarnings.mixin;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Screen.class)
public interface ScreenInvoker {
    @Invoker("addDrawableChild")
    @SuppressWarnings("UnusedReturnValue")
    <T extends Element & Drawable> T addDrawableChildInvoker(T drawableElement);
}
