package net.jasper.nokeybindwarnings;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoKeybindWarnings implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("nokeybindwarnings");

	public static CheckboxWidget toggleWarning = null;

	@Override
	public void onInitialize() {
		MinecraftClient client = MinecraftClient.getInstance();
		// textRenderer is Null if not executed later
		client.execute(() -> {
			toggleWarning = CheckboxWidget.builder(
					Text.translatable("nokeybindwarnings.checkbox.toggle"),
					client.textRenderer
			).pos(2, 2)
			.checked(false)
			.tooltip(Tooltip.of(Text.translatable("nokeybindwarnings.checkbox.toggle.tooltip")))
			.build();
			LOGGER.info("NoKeybindWarnings has been initialized");
		});
	}
}