package dev.cammiescorner.fireworkfrenzy.datagen.common;

import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyDamageTypes;
import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

//TODO move to sparkweave class eventually
public class FireworkFrenzyDamageTypeTagsProvider extends FabricTagProvider<DamageType> {
	public FireworkFrenzyDamageTypeTagsProvider(FabricDataOutput fabricOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(fabricOutput, Registries.DAMAGE_TYPE, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		getOrCreateTagBuilder(DamageTypeTags.AVOIDS_GUARDIAN_THORNS)
			.add(FireworkFrenzyDamageTypes.DAMAGE_CLOUD);

		getOrCreateTagBuilder(DamageTypeTags.IS_EXPLOSION)
			.add(FireworkFrenzyDamageTypes.DAMAGE_CLOUD);

		getOrCreateTagBuilder(DamageTypeTags.IS_PROJECTILE)
			.add(FireworkFrenzyDamageTypes.DAMAGE_CLOUD);

		getOrCreateTagBuilder(DamageTypeTags.NO_ANGER)
			.add(FireworkFrenzyDamageTypes.DAMAGE_CLOUD);

		getOrCreateTagBuilder(FireworkFrenzyTags.DamageTypes.IS_FIREWORK)
			.add(DamageTypes.FIREWORKS)
			.add(FireworkFrenzyDamageTypes.DAMAGE_CLOUD);
	}
}
