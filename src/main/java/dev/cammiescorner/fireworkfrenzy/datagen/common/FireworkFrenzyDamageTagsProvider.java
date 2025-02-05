package dev.cammiescorner.fireworkfrenzy.datagen.common;

import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;

import java.util.concurrent.CompletableFuture;

public class FireworkFrenzyDamageTagsProvider extends FabricTagProvider<DamageType> {
	public FireworkFrenzyDamageTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.DAMAGE_TYPE, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider arg) {
		getOrCreateTagBuilder(DamageTypeTags.AVOIDS_GUARDIAN_THORNS)
			.add(FireworkFrenzyDamageTypes.DAMAGE_CLOUD);

		getOrCreateTagBuilder(DamageTypeTags.IS_PROJECTILE)
			.add(FireworkFrenzyDamageTypes.DAMAGE_CLOUD);

		getOrCreateTagBuilder(DamageTypeTags.IS_EXPLOSION)
			.add(FireworkFrenzyDamageTypes.DAMAGE_CLOUD);

		getOrCreateTagBuilder(DamageTypeTags.NO_ANGER)
			.add(FireworkFrenzyDamageTypes.DAMAGE_CLOUD);
	}
}
