package dev.cammiescorner.fireworkfrenzy;

import com.teamresourceful.resourcefulconfig.api.annotations.Config;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;

@Config(FireworkFrenzy.MOD_ID)
public final class FireworkFrenzyConfig {
	@ConfigEntry(id = "crossbow_charge_time", translation = "config.fireworkfrenzy.crossbow_charge_time")
	public static int crossbowChargeTime = 20;
	@ConfigEntry(id = "quick_charge_modifier", translation = "config.fireworkfrenzy.quick_charge_modifier")
	public static int quickChargeModifier = 3;

	@ConfigEntry(id = "airstrike_jumping_charge_time", translation = "config.fireworkfrenzy.airstrike_jumping_charge_time")
	public static int airStrikeJumpingChargeTime = 2;
	@ConfigEntry(id = "airstrike_grounded_charge_time", translation = "config.fireworkfrenzy.airstrike_grounded_charge_time")
	public static int airStrikeGroundedChargeTime = 30;
	@ConfigEntry(id = "airstrike_damage_multiplier", translation = "config.fireworkfrenzy.airstrike_damage_multiplier")
	public static float airStrikeDamageMultiplier = 0.5f;

	@ConfigEntry(id = "mob_damage", translation = "config.fireworkfrenzy.mob_damage")
	public static float mobDamage = 3.0F;
	@ConfigEntry(id = "player_damage", translation = "config.fireworkfrenzy.player_damage")
	public static float playerDamage = 3.0F;
	@ConfigEntry(id = "fireball_damage_bonus", translation = "config.fireworkfrenzy.fireball_damage_bonus")
	public static float fireballDamageBonus = 6.0F;
	@ConfigEntry(id = "burst_disable_shield_chance", translation = "config.fireworkfrenzy.burst_disable_shield_chance")
	public static float burstDisableShieldChance = 0.25F;

	@ConfigEntry(id = "allow_rocket_jumping", translation = "config.fireworkfrenzy.allow_rocket_jumping")
	public static boolean allowRocketJumping = true;
	@ConfigEntry(id = "rocket_jump_knockback_multiplier", translation = "config.fireworkfrenzy.rocket_jump_knockback_multiplier")
	public static double rocketJumpKnockbackMultiplier = 1.0D;
	@ConfigEntry(id = "default_knockback_multiplier", translation = "config.fireworkfrenzy.default_knockback_multiplier")
	public static double defaultKnockbackMultiplier = 0.5D;
	@ConfigEntry(id = "air_strafe_speed_multiplier", translation = "config.fireworkfrenzy.air_strafe_speed_multiplier")
	public static float airStrafeSpeedMultiplier = 3.0F;
	@ConfigEntry(id = "elytra_cancels_rocket_jumping", translation = "config.fireworkfrenzy.elytra_cancels_rocket_jumping")
	public static boolean elytraCancelsRocketJumping = true;
	@ConfigEntry(id = "boost_cancels_rocket_jumping", translation = "config.fireworkfrenzy.boost_cancels_rocket_jumping")
	public static boolean boostCancelsRocketJumping = true;

	@ConfigEntry(id = "crossbow_allow_infinity_enchantment", translation = "config.fireworkfrenzy.crossbow_allow_infinity_enchantment")
	public static boolean crossbowAllowInfinityEnchantment = true;
	@ConfigEntry(id = "infinity_enchantment_affects_rockets", translation = "config.fireworkfrenzy.infinity_enchantment_affects_rockets")
	public static boolean infinityEnchantmentAffectsRockets = true;
	@ConfigEntry(id = "crossbow_use_rockets_from_inventory", translation = "config.fireworkfrenzy.crossbow_use_rockets_from_inventory")
	public static boolean crossbowUseRocketsFromInventory = true;

	@ConfigEntry(id = "rockets_have_damage_falloff", translation = "config.fireworkfrenzy.rockets_have_damage_falloff")
	public static boolean rocketsHaveDamageFalloff = true;
	@ConfigEntry(id = "rockets_damage_falloff_per_meter", translation = "config.fireworkfrenzy.rockets_damage_falloff_per_meter")
	public static float rocketDamageFalloffPerMeter = 1.0F;
	@ConfigEntry(id = "rocket_damage_falloff_min_multiplier", translation = "config.fireworkfrenzy.rocket_damage_falloff_min_multiplier")
	public static float minFalloffMultiplier = 0.3F;
	@ConfigEntry(id = "rocket_damage_falloff_start_distance", translation = "config.fireworkfrenzy.rocket_damage_falloff_start_distance")
	public static float rocketDamageFalloffStartDistance = 3.0F;

	@ConfigEntry(id = "show_firework_damage_tooltip", translation = "config.fireworkfrenzy.show_firework_damage_tooltip")
	public static boolean showFireworkDamageTooltip = true;
}
