package dev.cammiescorner.fireworkfrenzy.datagen.common;

import dev.cammiescorner.fireworkfrenzy.advancement.criterion.DoBlastJumpTrigger;
import dev.cammiescorner.fireworkfrenzy.advancement.criterion.MultiJumpTrigger;
import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyAdvancements;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class FireworkFrenzyAdvancementProvider extends FabricAdvancementProvider {
	public FireworkFrenzyAdvancementProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateAdvancement(Consumer<Advancement> writer) {
		var sonicBoom = Advancement.Builder.advancement().parent(new ResourceLocation("adventure/ol_betsy")).display(Items.FIREWORK_ROCKET, Component.translatable("advancements.fireworkfrenzy.adventure.sonic_boom.title"), Component.translatable("advancements.fireworkfrenzy.adventure.sonic_boom.description"), null, FrameType.CHALLENGE, true, true, false).addCriterion("do_blast_jump", DoBlastJumpTrigger.TriggerInstance.hasBlastJumpSpeed(3.43D)).save(writer, FireworkFrenzyAdvancements.SONIC_BOOM);
		var floorIsLava = Advancement.Builder.advancement().parent(sonicBoom).display(Items.LAVA_BUCKET, Component.translatable("advancements.fireworkfrenzy.adventure.the_floor_is_lava.title"), Component.translatable("advancements.fireworkfrenzy.adventure.the_floor_is_lava.description"), null, FrameType.CHALLENGE, true, true, false).addCriterion("do_multi_jump", MultiJumpTrigger.TriggerInstance.hasConsecutiveJumps(6)).save(writer, FireworkFrenzyAdvancements.THE_FLOOR_IS_LAVA);
	}
}
