package dev.cammiescorner.fireworkfrenzy.mixin.datagen;

import dev.cammiescorner.fireworkfrenzy.datagen.ext.AdvancementBuilderExt;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Consumer;

@Mixin(Advancement.Builder.class)
public abstract class AdvancementBuilderMixin implements AdvancementBuilderExt {

	@Shadow
	public abstract AdvancementHolder save(Consumer<AdvancementHolder> output, String id);

	@Override
	public AdvancementHolder fireworkfrenzy$save(Consumer<AdvancementHolder> output, ResourceLocation id) {
		return save(output, id.toString());
	}
}
