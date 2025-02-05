package dev.cammiescorner.fireworkfrenzy.enchantments;


import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class AirStrikeEnchantment extends Enchantment {
	public AirStrikeEnchantment() {
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
		return other != Enchantments.QUICK_CHARGE && super.checkCompatibility(other);
	}
}
