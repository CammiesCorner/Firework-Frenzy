package dev.cammiescorner.fireworkfrenzy;

import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyCriteriaTriggers;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyDataComponents;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEntityTypes;
import dev.upcraft.sparkweave.api.logging.SparkweaveLoggerFactory;
import dev.upcraft.sparkweave.api.platform.services.RegistryService;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.Logger;

public class FireworkFrenzy implements ModInitializer {
	public static final String MOD_ID = "fireworkfrenzy";
	public static final Logger LOGGER = SparkweaveLoggerFactory.getLogger();
	public static final Configurator CONFIGURATOR = new Configurator(MOD_ID);

	@Override
	public void onInitialize() {
		CONFIGURATOR.register(FireworkFrenzyConfig.class);

		var registryService = RegistryService.get();
		FireworkFrenzyDataComponents.DATA_COMPONENTS.accept(registryService);
//		FireworkFrenzyEnchantments.ENCHANTMENTS.accept(registryService);
		FireworkFrenzyEntityTypes.ENTITY_TYPES.accept(registryService);

		FireworkFrenzyCriteriaTriggers.register();
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
