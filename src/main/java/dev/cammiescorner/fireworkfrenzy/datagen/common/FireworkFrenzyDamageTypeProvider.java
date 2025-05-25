package dev.cammiescorner.fireworkfrenzy.datagen.common;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyDamageTypes;
import dev.upcraft.sparkweave.api.datagen.provider.SparkweaveDynamicRegistryEntryProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;

public class FireworkFrenzyDamageTypeProvider extends SparkweaveDynamicRegistryEntryProvider {
	@Override
	public void generate(RegistrySetBuilder builder) {
		builder.add(Registries.DAMAGE_TYPE, bootstapContext -> {
			bootstapContext.register(FireworkFrenzyDamageTypes.DAMAGE_CLOUD, new DamageType("fireworkfrenzy.damage_cloud", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 0.2F));
		});
	}

	@Override
	public String getName() {
		return FireworkFrenzy.MOD_ID;
	}
}
