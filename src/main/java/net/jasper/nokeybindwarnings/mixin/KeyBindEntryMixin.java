package net.jasper.nokeybindwarnings.mixin;

import net.jasper.nokeybindwarnings.NoKeybindWarnings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ControlsListWidget.KeyBindingEntry.class)
public class KeyBindEntryMixin {

	@Shadow @Final
	private ButtonWidget editButton;

	@Shadow @Final
	private ButtonWidget resetButton;

	@Mutable
	@Shadow @Final
	private Text bindingName;

	@Shadow @Final
	private KeyBinding binding;

	public KeyBindEntryMixin(Text bindingName) {
		this.bindingName = bindingName;
	}

	@SuppressWarnings("ConstantConditions")
    @Inject(method="render", at=@At("HEAD"), cancellable=true)
	private void injectedRender(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta, CallbackInfo ci) {
		if (NoKeybindWarnings.toggleWarning == null) {
			ci.cancel();
			return;
		}

		MinecraftClient client = MinecraftClient.getInstance();
		int maxKeyNameLength = 200;
		context.drawText(client.textRenderer, this.bindingName, x + 90 - maxKeyNameLength, y + entryHeight / 2 - client.textRenderer.fontHeight / 2, 0xFFFFFF, false);
		this.resetButton.setX(x + 190);
		this.resetButton.setY(y);
		this.resetButton.render(context, mouseX, mouseY, tickDelta);
		this.editButton.setX(x + 105);
		this.editButton.setY(y);

		// Disable Style if necessary
		boolean showWarnings = NoKeybindWarnings.toggleWarning.isChecked();
		Text oldMessage = this.editButton.getMessage();

		if (!showWarnings) {
			this.editButton.setMessage(this.binding.getBoundKeyLocalizedText());
		}

		this.editButton.render(context, mouseX, mouseY, tickDelta);
		this.editButton.setMessage(oldMessage);
		ci.cancel();
	}
}