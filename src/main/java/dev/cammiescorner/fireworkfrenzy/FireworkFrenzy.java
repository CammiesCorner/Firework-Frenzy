package dev.cammiescorner.fireworkfrenzy;

import com.teamresourceful.resourcefulconfig.common.config.Configurator;
import dev.cammiescorner.fireworkfrenzy.component.BlastJumper;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyCriteriaTriggers;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEnchantments;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEntityTypes;
import dev.upcraft.sparkweave.api.registry.RegistryService;
import dev.upcraft.sparkweave.api.util.logging.SparkweaveLoggerFactory;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.Logger;

public class FireworkFrenzy implements ModInitializer {
	public static final String MOD_ID = "fireworkfrenzy";
	public static final Logger LOGGER = SparkweaveLoggerFactory.getLogger();
	public static final Configurator CONFIGURATOR = new Configurator();
	public static final ThreadLocal<BlastJumper> BLAST_JUMPER_COMPONENT = new ThreadLocal<>();

	@Override
	public void onInitialize() {
		CONFIGURATOR.registerConfig(FireworkFrenzyConfig.class);

		var registryService = RegistryService.get();
		FireworkFrenzyEnchantments.ENCHANTMENTS.accept(registryService);
		FireworkFrenzyEntityTypes.ENTITY_TYPES.accept(registryService);

		FireworkFrenzyCriteriaTriggers.register();
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
