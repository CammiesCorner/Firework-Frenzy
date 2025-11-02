package dev.cammiescorner.fireworkfrenzy.data;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class FireworkFrenzyEnchantments {

	public static final ResourceKey<Enchantment> AIR_STRIKE = ResourceKey.create(Registries.ENCHANTMENT, FireworkFrenzy.id("air_strike"));
	public static final ResourceKey<Enchantment> FIXED_FUSE = ResourceKey.create(Registries.ENCHANTMENT, FireworkFrenzy.id("fixed_fuse"));
	public static final ResourceKey<Enchantment> TAKEOFF = ResourceKey.create(Registries.ENCHANTMENT, FireworkFrenzy.id("takeoff"));

	public static int getEnchantmentLevel(RegistryAccess registryAccess, ItemStack stack, ResourceKey<Enchantment> enchantmentKey) {
		var enchantment = registryAccess.registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(enchantmentKey);
		return EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack);
	}

	public static boolean hasAirStrike(RegistryAccess registryAccess, ItemStack stack) {
		return getEnchantmentLevel(registryAccess, stack, AIR_STRIKE) > 0;
	}

	public static boolean hasFixedFuse(RegistryAccess registryAccess, ItemStack stack) {
		return getEnchantmentLevel(registryAccess, stack, FIXED_FUSE) > 0;
	}
}
