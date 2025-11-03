package dev.cammiescorner.fireworkfrenzy.data;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzy;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.enchantment.Enchantment;

public class FireworkFrenzyTags {
	public static class DamageTypes {
		public static final TagKey<DamageType> IS_FIREWORK = TagKey.create(Registries.DAMAGE_TYPE, FireworkFrenzy.id("is_firework"));
	}

	public static class Enchantments {
		public static final TagKey<Enchantment> AIR_STRIKE_EXCLUSIVE_WITH = TagKey.create(Registries.ENCHANTMENT, FireworkFrenzy.id("exclusive_set/air_strike"));
		public static final TagKey<Enchantment> FIXED_FUSE_EXCLUSIVE_WITH = TagKey.create(Registries.ENCHANTMENT, FireworkFrenzy.id("exclusive_set/fixed_fuse"));
		public static final TagKey<Enchantment> TAKEOFF_EXCLUSIVE_WITH = TagKey.create(Registries.ENCHANTMENT, FireworkFrenzy.id("exclusive_set/takeoff"));
	}
}
