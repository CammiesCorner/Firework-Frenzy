package dev.cammiescorner.fireworkfrenzy.datagen.common;

import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyTags;
import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.concurrent.CompletableFuture;

//TODO move to sparkweave class eventually
public class FireworkFrenzyEnchantmentTagsProvider extends FabricTagProvider<Enchantment> {
	public FireworkFrenzyEnchantmentTagsProvider(FabricDataOutput fabricOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(fabricOutput, Registries.ENCHANTMENT, registriesFuture);
	}

	// TODO trades/* tags?
	@Override
	protected void addTags(HolderLookup.Provider arg) {
		getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
			.add(FireworkFrenzyEnchantments.AIR_STRIKE)
			.add(FireworkFrenzyEnchantments.FIXED_FUSE)
			.add(FireworkFrenzyEnchantments.TAKEOFF);

		getOrCreateTagBuilder(FireworkFrenzyTags.Enchantments.AIR_STRIKE_EXCLUSIVE_WITH)
			.add(FireworkFrenzyEnchantments.AIR_STRIKE)
			.add(Enchantments.QUICK_CHARGE);

		getOrCreateTagBuilder(FireworkFrenzyTags.Enchantments.FIXED_FUSE_EXCLUSIVE_WITH)
			.forceAddTag(EnchantmentTags.CROSSBOW_EXCLUSIVE);

		getOrCreateTagBuilder(FireworkFrenzyTags.Enchantments.TAKEOFF_EXCLUSIVE_WITH)
			.add(Enchantments.FEATHER_FALLING);
	}
}
