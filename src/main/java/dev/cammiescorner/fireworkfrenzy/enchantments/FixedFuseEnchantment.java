package dev.cammiescorner.fireworkfrenzy.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class FixedFuseEnchantment extends Enchantment {
	public FixedFuseEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentCategory.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
	}

	@Override
	public int getMinCost(int level) {
		return 23;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@Override
	protected boolean checkCompatibility(Enchantment other) {
		return other != Enchantments.MULTISHOT && super.checkCompatibility(other);
	}
}
