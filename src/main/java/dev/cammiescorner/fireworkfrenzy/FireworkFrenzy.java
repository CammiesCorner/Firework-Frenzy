package dev.cammiescorner.fireworkfrenzy;

import com.google.auto.service.AutoService;
import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyCriteriaTriggers;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyDataComponents;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEntityTypes;
import dev.upcraft.sparkweave.api.entrypoint.MainEntryPoint;
import dev.upcraft.sparkweave.api.platform.ModContainer;
import dev.upcraft.sparkweave.api.platform.services.RegistryService;
import net.minecraft.resources.ResourceLocation;

@AutoService(MainEntryPoint.class)
public class FireworkFrenzy implements MainEntryPoint {
	public static final String MOD_ID = "fireworkfrenzy";
	public static final Configurator CONFIGURATOR = new Configurator(MOD_ID);

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize(ModContainer mod) {
		CONFIGURATOR.register(FireworkFrenzyConfig.class);

		var registryService = RegistryService.get();
		FireworkFrenzyDataComponents.DATA_COMPONENTS.accept(registryService);
		FireworkFrenzyEntityTypes.ENTITY_TYPES.accept(registryService);
		FireworkFrenzyCriteriaTriggers.TRIGGERS.accept(registryService);
	}
}
