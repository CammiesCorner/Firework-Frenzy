package dev.cammiescorner.fireworkfrenzy.datagen.ext;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public interface AdvancementBuilderExt {

	default AdvancementHolder fireworkfrenzy$save(Consumer<AdvancementHolder> output, ResourceLocation id) {
		throw new UnsupportedOperationException();
	}
}
