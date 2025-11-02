package dev.cammiescorner.fireworkfrenzy.datagen;

import com.google.auto.service.AutoService;
import dev.cammiescorner.fireworkfrenzy.datagen.client.FireworkFrenzyEnglishLanguageProvider;
import dev.cammiescorner.fireworkfrenzy.datagen.common.*;
import dev.upcraft.sparkweave.api.datagen.ContextAwarePackOutput;
import dev.upcraft.sparkweave.api.datagen.DataGenerationContext;
import dev.upcraft.sparkweave.api.datagen.DynamicRegistryBuilder;
import dev.upcraft.sparkweave.api.entrypoint.DataGenerationEntryPoint;
import dev.upcraft.sparkweave.fabric.impl.datagen.FabricBuiltinPack;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.loader.api.FabricLoader;

@AutoService(DataGenerationEntryPoint.class)
public class FireworkFrenzyDatagenerator implements DataGenerationEntryPoint {
	@Override
	public void generateDynamicRegistryEntries(DynamicRegistryBuilder builder) {
		builder.add(FireworkFrenzyDamageTypeProvider::new);
		builder.add(FireworkFrenzyEnchantmentProvider::new);
	}

	@Override
	public void generate(DataGenerationContext ctx) {
		var pack = (FabricBuiltinPack) ctx.getDefaultPack();
		pack.addProvider(DataGenerationContext::includeServer, (output, registriesFuture) -> new FireworkFrenzyAdvancementProvider(toFabricOutput(output, ctx), registriesFuture));
		pack.addProvider(DataGenerationContext::includeServer, (output, registriesFuture) -> new FireworkFrenzyDamageTypeTagsProvider(toFabricOutput(output, ctx), registriesFuture));
		pack.addProvider(DataGenerationContext::includeServer, (output, registriesFuture) -> new FireworkFrenzyEnchantmentTagsProvider(toFabricOutput(output, ctx), registriesFuture));

		pack.addProvider(DataGenerationContext::includeServer, FireworkFrenzyEnglishLanguageProvider::new);
	}

	// TODO remove once sparkweave has this
	@Deprecated
	@SuppressWarnings("UnstableApiUsage")
	private static FabricDataOutput toFabricOutput(ContextAwarePackOutput contextAwarePackOutput, DataGenerationContext ctx) {
		return new FabricDataOutput(FabricLoader.getInstance().getModContainer(contextAwarePackOutput.getModContainer().metadata().id()).orElseThrow(), contextAwarePackOutput.getOutputFolder(), ctx.shouldValidate());
	}
}
