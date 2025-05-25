package dev.cammiescorner.fireworkfrenzy.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;

public class FireworkFrenzyDatagenerator implements DataGeneratorEntrypoint {
	// TODO do datagen stuff in general

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
//		SparkweaveDynamicRegistryEntryProvider.builder(FireworkFrenzy.MOD_ID)
//			.add(FireworkFrenzyDamageTypeProvider::new)
//			.build(registryBuilder);
	}

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
//		var pack = generator.createPack();
//		pack.addProvider((output, registriesFuture) -> SparkweaveDynamicRegistryEntryProvider.getGenerator(FireworkFrenzy.MOD_ID, output, registriesFuture));
//
//		pack.addProvider(FireworkFrenzyAdvancementProvider::new);
//		pack.addProvider(FireworkFrenzyDamageTagsProvider::new);
//
//		pack.addProvider(FireworkFrenzyEnglishLanguageProvider::new);
	}
}
