package dev.cammiescorner.fireworkfrenzy.init;

import com.mojang.serialization.Codec;
import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;

public class FireworkFrenzyDataComponents {
	public static final RegistryHandler<DataComponentType<?>> DATA_COMPONENTS = RegistryHandler.create(Registries.DATA_COMPONENT_TYPE, FireworkFrenzy.MOD_ID);

	public static final RegistrySupplier<DataComponentType<Boolean>> FIREBALL = DATA_COMPONENTS.register("fireball", () -> DataComponentType.<Boolean>builder()
		.persistent(Codec.BOOL)
		.networkSynchronized(ByteBufCodecs.BOOL)
		.cacheEncoding()
		.build()
	);
}
