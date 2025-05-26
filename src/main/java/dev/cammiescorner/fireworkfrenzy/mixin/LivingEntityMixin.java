package dev.cammiescorner.fireworkfrenzy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.cammiescorner.fireworkfrenzy.init.FireworkFrenzyComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@Shadow protected ItemStack useItem;

	@ModifyExpressionValue(method = "releaseUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;useOnRelease()Z"))
	private boolean handleAirstrike(boolean original) {
		if(this.useItem.getItem() instanceof CrossbowItem) {
			var component = FireworkFrenzyComponents.BLAST_JUMPER.getNullable(this);

			// TODO enchantment shenanigans
//			if(component != null && component.isBlastJumping() && EnchantmentHelper.getItemEnchantmentLevel(FireworkFrenzyEnchantments.AIR_STRIKE.holder(), this.useItem) > 0)
//				return false;
		}

		return original;
	}

	@ModifyExpressionValue(method = "updateUsingItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;useOnRelease()Z"))
	private boolean handleUpdateUsingItemAirstrike(boolean original, ItemStack stack) {
		if(stack.getItem() instanceof CrossbowItem) {
			var component = FireworkFrenzyComponents.BLAST_JUMPER.getNullable(this);

			// TODO enchantment shenanigans
//			if(component != null && component.isBlastJumping() && EnchantmentHelper.getItemEnchantmentLevel(FireworkFrenzyEnchantments.AIR_STRIKE.holder(), this.useItem) > 0)
//				return false;
		}

		return original;
	}
}
