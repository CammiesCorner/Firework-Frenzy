package dev.cammiescorner.fireworkfrenzy.datagen;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.datagen.client.FireworkFrenzyEnglishLanguageProvider;
import dev.cammiescorner.fireworkfrenzy.datagen.common.FireworkFrenzyAdvancementProvider;
import dev.cammiescorner.fireworkfrenzy.datagen.common.FireworkFrenzyDamageTagsProvider;
import dev.cammiescorner.fireworkfrenzy.datagen.common.FireworkFrenzyDamageTypeProvider;
import dev.upcraft.sparkweave.api.datagen.DynamicRegistryEntryProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;

public class FireworkFrenzyDatagenerator implements DataGeneratorEntrypoint {

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		DynamicRegistryEntryProvider.builder(FireworkFrenzy.MOD_ID)
			.add(FireworkFrenzyDamageTypeProvider::new)
			.build(registryBuilder);
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		var pack = generator.createPack();
		pack.addProvider((output, registriesFuture) -> DynamicRegistryEntryProvider.getGenerator(FireworkFrenzy.MOD_ID, output, registriesFuture));

		pack.addProvider(FireworkFrenzyAdvancementProvider::new);
		pack.addProvider(FireworkFrenzyDamageTagsProvider::new);

		pack.addProvider(FireworkFrenzyEnglishLanguageProvider::new);
	}
}
