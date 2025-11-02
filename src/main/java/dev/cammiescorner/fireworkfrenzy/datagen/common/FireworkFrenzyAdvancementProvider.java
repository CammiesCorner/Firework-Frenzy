package dev.cammiescorner.fireworkfrenzy.datagen.common;

import dev.cammiescorner.fireworkfrenzy.advancement.criterion.DoBlastJumpTrigger;
import dev.cammiescorner.fireworkfrenzy.advancement.criterion.MultiJumpTrigger;
import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyAdvancements;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

//TODO move to sparkweave class eventually
public class FireworkFrenzyAdvancementProvider extends FabricAdvancementProvider {
	public FireworkFrenzyAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	@SuppressWarnings("removal")
	@Override
	public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> writer) {
		var sonicBoom = Advancement.Builder.advancement().parent(ResourceLocation.withDefaultNamespace("adventure/ol_betsy")).display(Items.FIREWORK_ROCKET, Component.translatable("advancements.fireworkfrenzy.adventure.sonic_boom.title"), Component.translatable("advancements.fireworkfrenzy.adventure.sonic_boom.description"), null, AdvancementType.CHALLENGE, true, true, false).addCriterion("do_blast_jump", DoBlastJumpTrigger.TriggerInstance.hasBlastJumpSpeed(3.43D)).fireworkfrenzy$save(writer, FireworkFrenzyAdvancements.SONIC_BOOM);
		var floorIsLava = Advancement.Builder.advancement().parent(sonicBoom).display(Items.LAVA_BUCKET, Component.translatable("advancements.fireworkfrenzy.adventure.the_floor_is_lava.title"), Component.translatable("advancements.fireworkfrenzy.adventure.the_floor_is_lava.description"), null, AdvancementType.CHALLENGE, true, true, false).addCriterion("do_multi_jump", MultiJumpTrigger.TriggerInstance.hasConsecutiveJumps(6)).fireworkfrenzy$save(writer, FireworkFrenzyAdvancements.THE_FLOOR_IS_LAVA);
	}
}
