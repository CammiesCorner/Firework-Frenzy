package dev.cammiescorner.fireworkfrenzy.mixin;

import dev.cammiescorner.fireworkfrenzy.FireworkFrenzyConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;

// TODO this whole mixin is fuuuuucked. probably datapack now?
@Mixin(ArrowInfiniteEnchantment.class)
public abstract class ArrowInfiniteEnchantmentMixin extends Enchantment {
	private ArrowInfiniteEnchantmentMixin(Rarity weight, EnchantmentCategory type, EquipmentSlot[] slotTypes) {
		super(weight, type, slotTypes);
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean canEnchant(ItemStack stack) {
		return super.canEnchant(stack) || (FireworkFrenzyConfig.crossbowAllowInfinityEnchantment && EnchantmentCategory.CROSSBOW.canEnchant(stack.getItem()));
	}
}
