package dev.cammiescorner.fireworkfrenzy.datagen.client;

import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyAdvancements;
import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyDamageTypes;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEnchantments;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyEntityTypes;
import dev.upcraft.sparkweave.api.datagen.SparkweaveLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.locale.Language;

import java.util.concurrent.CompletableFuture;

public class FireworkFrenzyEnglishLanguageProvider extends SparkweaveLanguageProvider {
	public FireworkFrenzyEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(dataOutput, registriesFuture, Language.DEFAULT);
	}

	@Override
	public void generateTranslations(TranslationBuilder translationBuilder) {
		advancement(translationBuilder, FireworkFrenzyAdvancements.SONIC_BOOM, "Sonic Boom", "Through blast jumping, accelerate to the speed of sound (...or thereabouts)");

		damageType(translationBuilder, FireworkFrenzyDamageTypes.DAMAGE_CLOUD, "%s got blown up", "%s got blown up by %s", "%s got blown up by %s using %s");

		enchantment(translationBuilder, FireworkFrenzyEnchantments.AIR_STRIKE, "Air Strike", "Decreases the Crossbow charge time while rocket jumping");
		enchantment(translationBuilder, FireworkFrenzyEnchantments.FIXED_FUSE, "Fixed Fuse", "Removes the random fuse timer from Firework Rockets, allowing for mid-air jumps");
		enchantment(translationBuilder, FireworkFrenzyEnchantments.TAKEOFF, "Takeoff", "Removes self-damage from your own rockets");

		translationBuilder.add(FireworkFrenzyEntityTypes.DAMAGE_CLOUD.get(), "Damage Cloud");

		translationBuilder.add("tooltip.fireworkfrenzy.rocket_damage_base", "Damage: %s");
		translationBuilder.add("tooltip.fireworkfrenzy.rocket_damage_players", "Damage against Players: %s");
		translationBuilder.add("tooltip.fireworkfrenzy.rocket_has_fireball", "+Fireball");

		translationBuilder.add("config.fireworkfrenzy.crossbow_charge_time", "Time it takes for the Crossbow to load");
		translationBuilder.add("config.fireworkfrenzy.quick_charge_modifier", "Ticks per level quick charge removes from pull time");
		translationBuilder.add("config.fireworkfrenzy.airstrike_jumping_charge_time", "Air Strike charge time while rocket jumping");
		translationBuilder.add("config.fireworkfrenzy.airstrike_grounded_charge_time", "Air Strike charge time while grounded");
		translationBuilder.add("config.fireworkfrenzy.airstrike_damage_multiplier", "Air Strike damage multiplier while rocket jumping");
		translationBuilder.add("config.fireworkfrenzy.mob_damage", "Base firework rocket damage against mobs");
		translationBuilder.add("config.fireworkfrenzy.player_damage", "Base firework rocket damage against players");
		translationBuilder.add("config.fireworkfrenzy.fireball_damage_bonus", "Extra damage added by a fire charge (Explosive Enhancement Mod Required)");
		translationBuilder.add("config.fireworkfrenzy.burst_disable_shield_chance", "Chance for Burst fireworks to disable shields");
		translationBuilder.add("config.fireworkfrenzy.allow_rocket_jumping", "Allow rocket jumping");
		translationBuilder.add("config.fireworkfrenzy.rocket_jump_knockback_multiplier", "Rocket jump knockback multiplier");
		translationBuilder.add("config.fireworkfrenzy.default_knockback_multiplier", "Other entity knockback multiplier");
		translationBuilder.add("config.fireworkfrenzy.air_strafe_speed_multiplier", "Rocket jumping air strafe speed multiplier");
		translationBuilder.add("config.fireworkfrenzy.elytra_cancels_rocket_jumping", "Elytra flight cancels rocket jumping");
		translationBuilder.add("config.fireworkfrenzy.boost_cancels_rocket_jumping", "Boosting while elytra flying cancels rocket jumping");
		translationBuilder.add("config.fireworkfrenzy.crossbow_allow_infinity_enchantment", "Crossbows can have the Infinity enchantment");
		translationBuilder.add("config.fireworkfrenzy.infinity_enchantment_affects_rockets", "Infinity works on firework rockets");
		translationBuilder.add("config.fireworkfrenzy.crossbow_use_rockets_from_inventory", "Crossbows use firework rockets from your whole inventory");
		translationBuilder.add("config.fireworkfrenzy.rockets_have_damage_falloff", "Firework rockets have damage falloff");
		translationBuilder.add("config.fireworkfrenzy.rockets_damage_falloff_per_meter", "Damage falloff per meter from shooter");
		translationBuilder.add("config.fireworkfrenzy.rocket_damage_falloff_min_multiplier", "Minimum percentage of damage dealt at maximum falloff");
		translationBuilder.add("config.fireworkfrenzy.rocket_damage_falloff_start_distance", "Minimum distance away for damage falloff to apply");
		translationBuilder.add("config.fireworkfrenzy.show_firework_damage_tooltip", "Show damage tooltip for Firework Rocket items");
	}
}
