package dev.cammiescorner.fireworkfrenzy.init;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import dev.cammiescorner.fireworkfrenzy.enchantments.AirStrikeEnchantment;
import dev.cammiescorner.fireworkfrenzy.enchantments.FixedFuseEnchantment;
import dev.cammiescorner.fireworkfrenzy.enchantments.TakeoffEnchantment;
import dev.upcraft.sparkweave.api.registry.RegistryHandler;
import dev.upcraft.sparkweave.api.registry.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;

public class FireworkFrenzyEnchantments {
	public static final RegistryHandler<Enchantment> ENCHANTMENTS = RegistryHandler.create(Registries.ENCHANTMENT, FireworkFrenzy.MOD_ID);

	public static final RegistrySupplier<Enchantment> AIR_STRIKE = ENCHANTMENTS.register("air_strike", AirStrikeEnchantment::new);
	public static final RegistrySupplier<Enchantment> FIXED_FUSE = ENCHANTMENTS.register("fixed_fuse", FixedFuseEnchantment::new);
	public static final RegistrySupplier<Enchantment> TAKEOFF = ENCHANTMENTS.register("takeoff", TakeoffEnchantment::new);
}
