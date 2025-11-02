package dev.cammiescorner.fireworkfrenzy.init;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

public class FireworkFrenzyEnchantmentEffectComponents {

	public static final RegistryHandler<DataComponentType<?>> ENCHANTMENT_EFFECT_COMPONENTS = RegistryHandler.create(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, FireworkFrenzy.MOD_ID);
}
