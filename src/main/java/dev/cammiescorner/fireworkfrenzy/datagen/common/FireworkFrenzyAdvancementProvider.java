package dev.cammiescorner.fireworkfrenzy.datagen.common;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class FireworkFrenzyAdvancementProvider extends FabricAdvancementProvider {
	public FireworkFrenzyAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> writer) {
		// TODO do advancements
//		var sonicBoom = Advancement.Builder.advancement().parent(ResourceLocation.withDefaultNamespace("adventure/ol_betsy")).display(Items.FIREWORK_ROCKET, Component.translatable("advancements.fireworkfrenzy.adventure.sonic_boom.title"), Component.translatable("advancements.fireworkfrenzy.adventure.sonic_boom.description"), null, AdvancementType.CHALLENGE, true, true, false).addCriterion("do_blast_jump", DoBlastJumpTrigger.TriggerInstance.hasBlastJumpSpeed(3.43D)).save(writer, FireworkFrenzyAdvancements.SONIC_BOOM);
//		var floorIsLava = Advancement.Builder.advancement().parent(sonicBoom).display(Items.LAVA_BUCKET, Component.translatable("advancements.fireworkfrenzy.adventure.the_floor_is_lava.title"), Component.translatable("advancements.fireworkfrenzy.adventure.the_floor_is_lava.description"), null, AdvancementType.CHALLENGE, true, true, false).addCriterion("do_multi_jump", MultiJumpTrigger.TriggerInstance.hasConsecutiveJumps(6)).save(writer, FireworkFrenzyAdvancements.THE_FLOOR_IS_LAVA);
	}
}
