package dev.cammiescorner.fireworkfrenzy.datagen.client;

import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEntityTypes;
import dev.upcraft.sparkweave.api.datagen.ContextAwarePackOutput;
import dev.upcraft.sparkweave.api.datagen.TranslationBuilder;
import dev.upcraft.sparkweave.api.datagen.provider.SparkweaveLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class FireworkFrenzyEnglishLanguageProvider extends SparkweaveLanguageProvider {
	public FireworkFrenzyEnglishLanguageProvider(ContextAwarePackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture, String languageCode) {
		super(output, registriesFuture, languageCode);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registries, TranslationBuilder builder) {
		// TODO do advancements and enchantments
//		advancement(builder, FireworkFrenzyAdvancements.SONIC_BOOM, "Sonic Boom", "Through blast jumping, accelerate to the speed of sound (...or thereabouts)");
//		advancement(builder, FireworkFrenzyAdvancements.THE_FLOOR_IS_LAVA, "The floor is Lava", "Perform 6 consecutive blast jumps without touching the ground");
//
//		damageType(builder, FireworkFrenzyDamageTypes.DAMAGE_CLOUD, "%s got blown up", "%s got blown up by %s", "%s got blown up by %s using %s");
//
//		enchantment(builder, FireworkFrenzyEnchantments.AIR_STRIKE, "Air Strike", "Decreases the Crossbow charge time while rocket jumping");
//		enchantment(builder, FireworkFrenzyEnchantments.FIXED_FUSE, "Fixed Fuse", "Removes the random fuse timer from Firework Rockets, allowing for mid-air jumps");
//		enchantment(builder, FireworkFrenzyEnchantments.TAKEOFF, "Takeoff", "Removes self-damage from your own rockets");

		builder.entity(FireworkFrenzyEntityTypes.DAMAGE_CLOUD, "Damage Cloud");

		builder.add("tooltip.fireworkfrenzy.rocket_damage_base", "Damage: %s");
		builder.add("tooltip.fireworkfrenzy.rocket_damage_players", "Damage against Players: %s");
		builder.add("tooltip.fireworkfrenzy.rocket_has_fireball", "+Fireball");

		builder.add("config.fireworkfrenzy.crossbow_charge_time", "Time it takes for the Crossbow to load");
		builder.add("config.fireworkfrenzy.quick_charge_modifier", "Ticks per level quick charge removes from pull time");
		builder.add("config.fireworkfrenzy.airstrike_jumping_charge_time", "Air Strike charge time while rocket jumping");
		builder.add("config.fireworkfrenzy.airstrike_grounded_charge_time", "Air Strike charge time while grounded");
		builder.add("config.fireworkfrenzy.airstrike_damage_multiplier", "Air Strike damage multiplier while rocket jumping");
		builder.add("config.fireworkfrenzy.mob_damage", "Base firework rocket damage against mobs");
		builder.add("config.fireworkfrenzy.player_damage", "Base firework rocket damage against players");
		builder.add("config.fireworkfrenzy.fireball_damage_bonus", "Extra damage added by a fire charge (Explosive Enhancement Mod Required)");
		builder.add("config.fireworkfrenzy.burst_disable_shield_chance", "Chance for Burst fireworks to disable shields");
		builder.add("config.fireworkfrenzy.allow_rocket_jumping", "Allow rocket jumping");
		builder.add("config.fireworkfrenzy.rocket_jump_knockback_multiplier", "Rocket jump knockback multiplier");
		builder.add("config.fireworkfrenzy.default_knockback_multiplier", "Other entity knockback multiplier");
		builder.add("config.fireworkfrenzy.air_strafe_speed_multiplier", "Rocket jumping air strafe speed multiplier");
		builder.add("config.fireworkfrenzy.elytra_cancels_rocket_jumping", "Elytra flight cancels rocket jumping");
		builder.add("config.fireworkfrenzy.boost_cancels_rocket_jumping", "Boosting while elytra flying cancels rocket jumping");
		builder.add("config.fireworkfrenzy.crossbow_allow_infinity_enchantment", "Crossbows can have the Infinity enchantment");
		builder.add("config.fireworkfrenzy.infinity_enchantment_affects_rockets", "Infinity works on firework rockets");
		builder.add("config.fireworkfrenzy.crossbow_use_rockets_from_inventory", "Crossbows use firework rockets from your whole inventory");
		builder.add("config.fireworkfrenzy.rockets_have_damage_falloff", "Firework rockets have damage falloff");
		builder.add("config.fireworkfrenzy.rockets_damage_falloff_per_meter", "Damage falloff per meter from shooter");
		builder.add("config.fireworkfrenzy.rocket_damage_falloff_min_multiplier", "Minimum percentage of damage dealt at maximum falloff");
		builder.add("config.fireworkfrenzy.rocket_damage_falloff_start_distance", "Minimum distance away for damage falloff to apply");
		builder.add("config.fireworkfrenzy.show_firework_damage_tooltip", "Show damage tooltip for Firework Rocket items");
	}
}
