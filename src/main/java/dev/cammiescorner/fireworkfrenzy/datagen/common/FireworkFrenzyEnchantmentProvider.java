package dev.cammiescorner.fireworkfrenzy.datagen.common;

import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyEnchantments;
import dev.cammiescorner.fireworkfrenzy.data.FireworkFrenzyTags;
import dev.upcraft.sparkweave.api.datagen.provider.SparkweaveEnchantmentProvider;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.effects.DamageImmunity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;

public class FireworkFrenzyEnchantmentProvider extends SparkweaveEnchantmentProvider {
	@Override
	protected void generateEnchantments(Context ctx, HolderGetter<DamageType> damageTypes, HolderGetter<Enchantment> enchantments, HolderGetter<Item> items, HolderGetter<Block> blocks) {
		ctx.register(FireworkFrenzyEnchantments.AIR_STRIKE, Enchantment.enchantment(
				Enchantment.definition(
					items.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
					1,
					1,
					Enchantment.constantCost(23),
					Enchantment.constantCost(60),
					8,
					EquipmentSlotGroup.MAINHAND
				))
				.exclusiveWith(enchantments.getOrThrow(FireworkFrenzyTags.Enchantments.AIR_STRIKE_EXCLUSIVE_WITH)),
			"Air Strike",
			"Decreases the Crossbow charge time while rocket jumping"
		);

		ctx.register(FireworkFrenzyEnchantments.FIXED_FUSE, Enchantment.enchantment(
				Enchantment.definition(
					items.getOrThrow(ItemTags.CROSSBOW_ENCHANTABLE),
					1,
					1,
					Enchantment.constantCost(23),
					Enchantment.constantCost(60),
					8,
					EquipmentSlotGroup.MAINHAND
				))
				.exclusiveWith(enchantments.getOrThrow(FireworkFrenzyTags.Enchantments.FIXED_FUSE_EXCLUSIVE_WITH)),
			"Fixed Fuse",
			"Removes the random fuse timer from Firework Rockets, allowing for mid-air jumps"
		);

		ctx.register(FireworkFrenzyEnchantments.TAKEOFF, Enchantment.enchantment(
			Enchantment.definition(
				items.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE),
				1,
				1,
				Enchantment.constantCost(23),
				Enchantment.constantCost(60),
				8,
				EquipmentSlotGroup.FEET
			))
			.exclusiveWith(enchantments.getOrThrow(FireworkFrenzyTags.Enchantments.TAKEOFF_EXCLUSIVE_WITH))
				.withEffect(EnchantmentEffectComponents.DAMAGE_IMMUNITY, new DamageImmunity(), DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType()
					.isDirect(false)
					.tag(TagPredicate.is(FireworkFrenzyTags.DamageTypes.IS_FIREWORK))
					.source(EntityPredicate.Builder.entity()
						// distance to source entity should be 0 if it is the entity itself
						.distance(DistancePredicate.absolute(MinMaxBounds.Doubles.atMost(Mth.EPSILON)))
					)
				)),
			"Takeoff",
			"Removes self-damage from your own rockets"
		);
	}
}
